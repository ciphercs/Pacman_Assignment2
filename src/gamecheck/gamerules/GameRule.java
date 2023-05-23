package gamecheck.gamerules;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class GameRule {
    protected ArrayList<File> maps;
    protected ArrayList<String> fileNames;
    protected File directory;
    protected String errorMessage;

    public GameRule(File directory) {
        this.directory = directory;
        this.maps = new ArrayList<>(Arrays.asList(Objects.requireNonNull(directory.listFiles())));
        this.fileNames = this.getFileNames();
    }

    // true if rule is satisfied
    public abstract boolean checkRule();

    public String getErrorMessage() {
        return errorMessage;
    }

    protected ArrayList<String> getFileNames() {
        ArrayList<String> fileNames = new ArrayList<>();
        for (File map : maps) {
            fileNames.add(map.getName());
        }
        return fileNames;
    }
}
