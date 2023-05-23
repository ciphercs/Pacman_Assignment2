package game.check;

import game.check.gamerules.*;

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
                // TODO: add error message to log file
                return false;
            }
        }
        return true;
    }
}
