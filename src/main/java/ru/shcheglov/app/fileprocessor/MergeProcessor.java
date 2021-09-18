package ru.shcheglov.app.fileprocessor;

import ru.shcheglov.app.TxtMergerApp;
import ru.shcheglov.app.util.DataValidator;
import ru.shcheglov.app.util.Utils;

import java.io.*;
import java.util.*;

public class MergeProcessor<T extends Comparable<T>> {
    private PrintWriter writer;
    private final List<BufferedReader> readers;
    private final Comparator<T> comparator;
    private final DataValidator<T> validator;
    private final Class<T> elementsClass;

    public MergeProcessor(TxtMergerApp app, Class<T> elementsClass) {
        this.elementsClass = elementsClass;
        this.readers = new ArrayList<>(app.getInputFiles().length);
        this.comparator = app.isDescending() ? (o1, o2) -> -o1.compareTo(o2) : Comparator.naturalOrder();
        this.validator = new DataValidator<>(comparator, elementsClass);

        try {
            this.writer = new PrintWriter(app.getOutputFile());
        } catch (FileNotFoundException e) {
            System.err.printf("Файл %s%n не найден! Программа остановлена...%n", app.getOutputFile().getPath());
            System.exit(1);
        }

        for (File file : app.getInputFiles()) {
            try {
                readers.add(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException e) {
                System.err.printf("Файл %s не найден! Программа остановлена...%n", file.getPath());
                System.exit(1);
            }
        }
    }

    public void merge() throws IOException {
        List<T> currentElements = new ArrayList<>(readers.size());
        List<T> prevElements = new ArrayList<>(readers.size());

        for (int i = 0; i < readers.size(); i++) {
            prevElements.add(null);
            currentElements.add(getElement(readers.get(i), prevElements.get(i)));
        }

        while (readers.size() != 0) {
            int indexOfWrittenElement = writeElementToFile(currentElements);

            if (readers.get(indexOfWrittenElement).ready()) {
                prevElements.set(indexOfWrittenElement, currentElements.get(indexOfWrittenElement));
                currentElements.set(indexOfWrittenElement,
                        getElement(readers.get(indexOfWrittenElement), prevElements.get(indexOfWrittenElement)));
            } else {
                currentElements.remove(indexOfWrittenElement);
                prevElements.remove(indexOfWrittenElement);
                readers.get(indexOfWrittenElement).close();
                readers.remove(indexOfWrittenElement);
            }
        }

        writer.close();
        Utils.printSuccessMessage();
    }

    private int writeElementToFile(List<T> listOfElements) {
        if (listOfElements.size() == 1) {
            writer.println(listOfElements.get(0));
            writer.flush();
            return 0;
        }

        T element = listOfElements.get(0);

        for (int i = 0; i < listOfElements.size() - 1; i++) {
            element = comparator.compare(element, listOfElements.get(i + 1)) < 0 ? element : listOfElements.get(i + 1);
        }

        writer.println(element);
        writer.flush();

        return listOfElements.indexOf(element);
    }


    private T getElement(BufferedReader reader, T prevElement) throws IOException {
        if (elementsClass == Integer.class) {
            String line = reader.readLine();
            boolean isSorted;

            do {
                while (reader.ready() && !validator.IsIntegerElement(line)) {
                    line = reader.readLine();
                }

                isSorted = validator.isSorted(prevElement, elementsClass.cast(Integer.valueOf(line)));

                if (!isSorted && reader.ready()) {
                    line = reader.readLine();
                }
            } while (!isSorted);

            return elementsClass.cast(Integer.valueOf(line));
        }

        return elementsClass.cast(reader.readLine());
    }
}
