package ru.shcheglov.app.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Util {
    public static List<Integer> getListWithIntegers(File[] files) {
        List<Integer> list = new ArrayList<>();
        BufferedReader reader;

        try {
            for (File file : files) {
                reader = new BufferedReader(new FileReader(file));
                list.addAll(reader.lines().map(Integer::valueOf).collect(Collectors.toList()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Integer> getListWithIntegers(File file) {
        List<Integer> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            list = reader.lines().map(Integer::valueOf).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<String> getListWithStrings(File[] files) {
        List<String> list = new ArrayList<>();
        BufferedReader reader;

        try {
            for (File file : files) {
                reader = new BufferedReader(new FileReader(file));
                list.addAll(reader.lines().collect(Collectors.toList()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<String> getListWithStrings(File file) {
        List<String> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            list = reader.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
