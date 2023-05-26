package src.levelcheck;

import java.io.File;
import java.util.ArrayList;

public class LevelChecker {
    private File directory;
    private src.utility.GameCallback logger;
    public LevelChecker(File directory, src.utility.GameCallback logger) {
        this.directory = directory;
        this.logger = logger;
    }

    // return true if all game rules are satisfied
    public boolean checkLevelRules() {
        ArrayList<LevelRule> rules = new ArrayList<>();

        rules.add(new StartPacLevelCheck(directory));
        rules.add(new PortalLevelCheck(directory));
        rules.add(new GoldPillLevelCheck(directory));
        rules.add(new GoldPillAccessCheck(directory));
        for (LevelRule rule : rules) {
            if (!rule.checkRule()) {
                this.generateLog(rule);
                //return false;
            }
        }
        return true;
    }

    private void generateLog(LevelRule rule) {

        for(String msg: rule.getErrorMessage()) {
            logger.writeString(msg);
            System.out.println(msg);
        }
    }
}
