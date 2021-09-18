package ru.shcheglov.app;

import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import ru.shcheglov.app.fileprocessor.MergeProcessor;

@Command(name = "TxtMerger",
        mixinStandardHelpOptions = true,
        version = "TxtMerger 1.0",
        description = "TxtMerger is a small command line application that allow sort and merge txt files",
        footer = "Created by Stanislav Shcheglov for CFT Shift")
public class TxtMergerApp extends ConsoleAttributes implements Callable<Integer> {
    public static void main(String... args) {
        System.exit(runConsole(args));
    }

    private static int runConsole(String... args) {
        return new CommandLine(new TxtMergerApp()).execute(args);
    }

    @Override
    public Integer call() throws IOException {
        MergeProcessor<?> processor = isIntegerType() ?
                new MergeProcessor<>(this, Integer.class) : new MergeProcessor<>(this, String.class);
        processor.merge();
        return 0;
    }
}
