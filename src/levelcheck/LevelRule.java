package levelcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class LevelRule {
    protected File directory;
    protected List<String> errorMessage = new ArrayList<>();

    public LevelRule(File directory) {
        this.directory = directory;
    }

    // true if rule is satisfied
    public abstract boolean checkRule();

    public List<String> getErrorMessage() {
        return errorMessage;
    }
}
