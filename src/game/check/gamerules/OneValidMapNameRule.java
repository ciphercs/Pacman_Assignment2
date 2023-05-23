package game.check.gamerules;

import java.io.File;

public class OneValidMapNameRule extends GameRule {

    public OneValidMapNameRule(File directory) {
        super(directory);
        this.errorMessage = "Game " + directory.getName() + " - no maps found";
    }

    @Override
    public boolean checkRule() {
        for (String fileName : fileNames) {
            Character firstChar = fileName.charAt(0);
            if (!Character.isDigit(firstChar)) {
                return false;
            }
        }
        return true;
    }
}
