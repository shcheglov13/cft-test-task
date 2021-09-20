package ru.shcheglov.app;

import org.junit.jupiter.api.Test;
import ru.shcheglov.app.utils.Util;

import java.io.*;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TxtMergerAppTest {

    @Test
    public void testMergeAscSortIntegers() {
        URL resIn1 = getClass().getClassLoader().getResource("test_data/AscOrderIntegers_in1.txt");
        URL resIn2 = getClass().getClassLoader().getResource("test_data/AscOrderIntegers_in2.txt");
        URL resOut = getClass().getClassLoader().getResource("output.txt");

        assert resIn1 != null;
        assert resIn2 != null;
        assert resOut != null;

        File[] inputFiles = {new File(resIn1.getPath()), new File(resIn2.getPath())};
        File outputFile = new File(resOut.getPath());

        TxtMergerApp app = new TxtMergerApp();
        app.setInputFiles(inputFiles);
        app.setOutputFile(outputFile);
        app.setIntegerType(true);
        app.call();

        List<Integer> output = Util.getListWithIntegers(outputFile);
        List<Integer> expected = Util.getListWithIntegers(inputFiles);
        expected.sort(Comparator.naturalOrder());

        assertEquals(expected, output);
    }

    @Test
    public void testMergeDscSortIntegers() {
        URL resIn1 = getClass().getClassLoader().getResource("test_data/DscOrderIntegers_in1.txt");
        URL resIn2 = getClass().getClassLoader().getResource("test_data/DscOrderIntegers_in2.txt");
        URL resOut = getClass().getClassLoader().getResource("output.txt");

        assert resIn1 != null;
        assert resIn2 != null;
        assert resOut != null;

        File[] inputFiles = {new File(resIn1.getPath()), new File(resIn2.getPath())};
        File outputFile = new File(resOut.getPath());

        TxtMergerApp app = new TxtMergerApp();
        app.setInputFiles(inputFiles);
        app.setOutputFile(outputFile);
        app.setIntegerType(true);
        app.setDescending(true);
        app.call();

        List<Integer> output = Util.getListWithIntegers(outputFile);
        List<Integer> expected = Util.getListWithIntegers(inputFiles);
        expected.sort(Comparator.reverseOrder());

        assertEquals(expected, output);
    }

    @Test
    public void testMergeAscSortStrings() {
        URL resIn1 = getClass().getClassLoader().getResource("test_data/AscOrderStrings_in1.txt");
        URL resIn2 = getClass().getClassLoader().getResource("test_data/AscOrderStrings_in2.txt");
        URL resOut = getClass().getClassLoader().getResource("output.txt");

        assert resIn1 != null;
        assert resIn2 != null;
        assert resOut != null;

        File[] inputFiles = {new File(resIn1.getPath()), new File(resIn2.getPath())};
        File outputFile = new File(resOut.getPath());

        TxtMergerApp app = new TxtMergerApp();
        app.setInputFiles(inputFiles);
        app.setOutputFile(outputFile);
        app.setIntegerType(false);
        app.call();

        List<String> output = Util.getListWithStrings(outputFile);
        List<String> expected = Util.getListWithStrings(inputFiles);
        expected.sort(Comparator.naturalOrder());

        assertEquals(expected, output);
    }

    @Test
    public void testMergeDscSortStrings() {
        URL resIn1 = getClass().getClassLoader().getResource("test_data/DscOrderStrings_in1.txt");
        URL resIn2 = getClass().getClassLoader().getResource("test_data/DscOrderStrings_in2.txt");
        URL resOut = getClass().getClassLoader().getResource("output.txt");

        assert resIn1 != null;
        assert resIn2 != null;
        assert resOut != null;

        File[] inputFiles = {new File(resIn1.getPath()), new File(resIn2.getPath())};
        File outputFile = new File(resOut.getPath());

        TxtMergerApp app = new TxtMergerApp();
        app.setInputFiles(inputFiles);
        app.setOutputFile(outputFile);
        app.setIntegerType(false);
        app.setDescending(true);
        app.call();

        List<String> output = Util.getListWithStrings(outputFile);
        List<String> expected = Util.getListWithStrings(inputFiles);
        expected.sort(Comparator.reverseOrder());

        assertEquals(expected, output);
    }
}
