package ru.shcheglov.app;

import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import ru.shcheglov.app.fileprocessor.TxtProcessor;

@Command(name = "TxtSorter",
        mixinStandardHelpOptions = true,
        version = "TxtSorter 1.0",
        description = "TxtSorter is a small command line application that allow sort txt files",
        footer = "Created by Stanislav Shcheglov for CFT Shift")
public class TxtSorterApp extends ConsoleAttributes implements Callable<Integer> {
    public static void main(String... args) {
        System.exit(runConsole(args));
    }

    protected static int runConsole(String... args) {
        return new CommandLine(new TxtSorterApp()).execute(args);
    }

    @Override
    public Integer call() throws IOException {
        TxtProcessor txtProcessor = new TxtProcessor();
        txtProcessor.processFiles(this, getInputFiles());
        return 0;
    }
}
