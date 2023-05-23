package gamecheck;

import gamecheck.gamerules.GameRule;
import gamecheck.gamerules.OneValidMapNameRule;
import gamecheck.gamerules.WellDefinedMapSeqRule;

import java.io.File;
import java.util.ArrayList;

public class GameChecker {
    private File directory;

    public GameChecker(File directory) {
        this.directory = directory;
    }

    // return true if all game rules are satisfied
    public boolean checkGameRules() {
        ArrayList<GameRule> rules = new ArrayList<>();

        rules.add(new OneValidMapNameRule(directory));
        rules.add(new WellDefinedMapSeqRule(directory));

        for (GameRule rule : rules) {
            if (!rule.checkRule()) {
                this.generateLog(rule);
                return false;
            }
        }
        return true;
    }

    private void generateLog(GameRule rule) {
        src.utility.GameCallback gameCallback = new src.utility.GameCallback();
        gameCallback.writeString(rule.getErrorMessage());
    }
}
