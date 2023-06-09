package levelcheck;

import java.io.File;
import java.util.ArrayList;

public class LevelChecker {
    private File directory;
    private src.utility.GameCallback logger;
    public LevelChecker(File directory, src.utility.GameCallback logger) {
        this.directory = directory;
        this.logger = logger;
    }

    // return true if all level rules are satisfied
    public boolean checkLevelRules() {
        ArrayList<LevelRule> rules = new ArrayList<>();
        boolean levelPassed = true;

        rules.add(new StartPacLevelCheck(directory));
        rules.add(new PortalLevelCheck(directory));
        rules.add(new GoldPillLevelCheck(directory));
        rules.add(new GoldPillAccessCheck(directory));
        for (LevelRule rule : rules) {
            if (!rule.checkRule()) {
                this.generateLog(rule);
                levelPassed = false;
            }
        }
        return levelPassed;
    }

    private void generateLog(LevelRule rule) {

        for(String msg: rule.getErrorMessage()) {
            logger.writeString(msg);
        }
    }
}
