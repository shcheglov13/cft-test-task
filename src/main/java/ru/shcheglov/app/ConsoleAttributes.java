package ru.shcheglov.app;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ArgGroup;

import java.io.File;

public class ConsoleAttributes {
    public static final String ASCENDING_OPTION = "-a";
    public static final String DESCENDING_OPTION = "-d";
    public static final String INTEGER_DATA_TYPE_OPTION = "-i";
    public static final String STRING_DATA_TYPE_OPTION = "-s";
    public static final String OUTPUT_FILE_OPTION = "-out";

    static class DataType {
        @Option(names = INTEGER_DATA_TYPE_OPTION, required = true, description = "Integer data type option")
        private boolean isIntegerType;

        @Option(names = STRING_DATA_TYPE_OPTION, required = true, description = "String data type option")
        private boolean isStringType;

        public void setIntegerType(boolean isIntegerType) {
            this.isIntegerType = isIntegerType;
        }

        public void setStringType(boolean isStringType) {
            this.isStringType = isStringType;
        }
    }

    @ArgGroup(multiplicity = "1")
    DataType args;

    @Option(names = ASCENDING_OPTION, description = "Ascending option")
    private boolean isAscending;

    @Option(names = DESCENDING_OPTION, description = "Descending option")
    private boolean isDescending;

    @Option(names = OUTPUT_FILE_OPTION, paramLabel = "outputFile", description = "output file", required = true)
    private File outputFile;

    @Parameters(paramLabel = "[input files]", description = "input files", arity = "1...")
    private File[] inputFiles;

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

    public boolean isIntegerType() {
        return args.isIntegerType;
    }

    public boolean isStringType() {
        return args.isStringType;
    }

    public void setIntegerType(boolean isIntegerType) {
        this.args = new DataType();
        this.args.setIntegerType(isIntegerType);
    }

    public void setStringType(boolean isStringType) {
        this.args = new DataType();
        this.args.setStringType(isStringType);
    }

    public void setAscending(boolean isAscending) {
        this.isAscending = isAscending;
    }

    public void setDescending(boolean isDescending) {
        this.isDescending = isDescending;
    }
}
