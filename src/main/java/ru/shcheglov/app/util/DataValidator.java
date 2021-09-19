package ru.shcheglov.app.util;

import ru.shcheglov.app.exceptions.BlankFileException;
import ru.shcheglov.app.exceptions.InvalidSortException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;

public class DataValidator<T> {
    private final Comparator<T> comparator;
    private final Class<T> elementsClass;

    public DataValidator(Comparator<T> comparator, Class<T> elementsClass) {
        this.comparator = comparator;
        this.elementsClass = elementsClass;
    }

    public boolean isValidElement(T prevElement, String currentElement) {
        if (elementsClass == Integer.class) {
            try {
                Integer current = Integer.valueOf(currentElement);

                if (prevElement == null) {
                    return true;
                }

                if (!(comparator.compare(prevElement, elementsClass.cast(current)) <= 0)) {
                    throw new InvalidSortException("Нарушена сортировка файла! Элемент \"" + currentElement + "\" будет пропущен");
                }
            } catch (NumberFormatException e) {
                System.err.printf("Элемент %s не является целым числом и будет пропущен!%n", currentElement);
                return false;
            } catch (InvalidSortException e) {
                System.err.println(e.getMessage());
                return false;
            }
        }

        return true;
    }

    public boolean isNotBlankFile(BufferedReader reader) throws IOException, BlankFileException {
        String line;

        while (reader.ready()) {
            line = reader.readLine();
            if (line != null && !line.isBlank()) {
                return true;
            }
        }

        throw new BlankFileException("");
    }
}
