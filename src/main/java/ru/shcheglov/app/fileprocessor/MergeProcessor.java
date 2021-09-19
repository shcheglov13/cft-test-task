package ru.shcheglov.app.fileprocessor;

import ru.shcheglov.app.TxtMergerApp;
import ru.shcheglov.app.exceptions.BlankFileException;
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

    public MergeProcessor(TxtMergerApp app, Class<T> elementsClass) throws IOException {
        this.elementsClass = elementsClass;
        this.readers = new ArrayList<>(app.getInputFiles().length);
        this.comparator = app.isDescending() ? Comparator.reverseOrder() : Comparator.naturalOrder();
        this.validator = new DataValidator<>(comparator, elementsClass);

        try {
            if (app.getOutputFile() != null) {
                this.writer = new PrintWriter(app.getOutputFile());
            } else {
                System.err.printf("Файл для записи данных не найден! Программа остановлена...%n");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.err.printf("Файл %s%n не найден! Программа остановлена...%n", app.getOutputFile().getPath());
            System.exit(1);
        }

        for (File file : app.getInputFiles()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                if (validator.isNotBlankFile(reader)) {
                    readers.add(new BufferedReader(new FileReader(file)));
                }
            } catch (FileNotFoundException e) {
                System.err.printf("Файл %s не найден! Программа остановлена...%n", file.getPath());
                System.exit(1);
            } catch (BlankFileException e) {
                System.err.printf("Файл %s не содержит данных и будет исключен из обработки%n", file.getPath());
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

                if (currentElements.get(indexOfWrittenElement) == null) {
                    removeElementFromListsAndCloseReader(prevElements, currentElements, indexOfWrittenElement);
                }
            } else {
                removeElementFromListsAndCloseReader(prevElements, currentElements, indexOfWrittenElement);
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
            if (element != null) {
                element = comparator.compare(element, listOfElements.get(i + 1)) < 0 ? element : listOfElements.get(i + 1);
            }
        }

        writer.println(element);
        writer.flush();

        return listOfElements.indexOf(element);
    }

    private T getElement(BufferedReader reader, T prevElement) throws IOException {
        String line = reader.readLine();
        boolean isValid;

        while (!(isValid = validator.isValidElement(prevElement, line)) && reader.ready()) {
            line = reader.readLine();
        }

        if (!isValid && !reader.ready()) {
            return null;
        }

        if (elementsClass == Integer.class) {
            return elementsClass.cast(Integer.valueOf(line));
        }

        return elementsClass.cast(line);
    }

    private void removeElementFromListsAndCloseReader(List<T> prevElements, List<T> currentElements, int index)
            throws IOException {
        currentElements.remove(index);
        prevElements.remove(index);
        readers.get(index).close();
        readers.remove(index);
    }
}
