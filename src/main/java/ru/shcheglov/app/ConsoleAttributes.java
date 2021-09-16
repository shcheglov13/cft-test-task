package ru.shcheglov.app;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

public class ConsoleAttributes {
    public static final String ASCENDING_OPTION = "-a";
    public static final String DESCENDING_OPTION = "-d";
    public static final String INTEGER_DATA_TYPE_OPTION = "-i";
    public static final String STRING_DATA_TYPE_OPTION = "-s";

    @Option(names = INTEGER_DATA_TYPE_OPTION, description = "Integer data type option")
    private boolean isIntegerType;

    @Option(names = STRING_DATA_TYPE_OPTION, description = "String data type option")
    private boolean isStringType;

    @Option(names = ASCENDING_OPTION, description = "Ascending option")
    private boolean isAscending;

    @Option(names = DESCENDING_OPTION, description = "Descending option")
    private boolean isDescending;

    @Parameters(paramLabel = "outputFile", description = "output file")
    private File outputFile;

    @Parameters(paramLabel = "inputFiles", description = "input files")
    private File[] inputFiles;

    public boolean isIntegerType() {
        return isIntegerType;
    }

    public boolean isStringType() {
        return isStringType;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public boolean isDescending() {
        return isDescending;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public File[] getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(File[] inputFiles) {
        this.inputFiles = inputFiles;
    }
}
