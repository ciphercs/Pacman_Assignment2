package game.check.gamerules;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class WellDefinedMapSeqRule extends GameRule {
    private ArrayList<Integer> firstChars;

    public WellDefinedMapSeqRule(File directory) {
        super(directory);
        this.errorMessage = "Game " + directory.getName() + " - multiple maps at the same level:";
    }

    @Override
    public boolean checkRule() {
        this.firstChars = getFirstChars();
        int max = Collections.max(firstChars);
        ArrayList<Boolean> characterFound = new ArrayList<>();
        ArrayList<String> invalidMaps;

        // true if already found a map there
        for (int i=0; i<max; i++) {
            characterFound.add(false);
        }

        for (int chr : firstChars) {
            if (characterFound.get(chr-1) == false) {
                characterFound.add(chr-1, true);
            } else if (characterFound.get(chr-1) == true) {
                // update error message
                invalidMaps = getInvalidFiles(chr);
                this.errorMessage += (" " + invalidMaps.get(0));
                for (int i=1; i<invalidMaps.size(); i++) {
                    this.errorMessage += ("; " +  invalidMaps.get(i));
                }
                return false;
            }
        }
        return true;
    }

    private ArrayList<Integer> getFirstChars() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (String filename : fileNames) {
            arr.add(Integer.parseInt(String.valueOf(filename.charAt(0))));
        }
        return arr;
    }

    private ArrayList<String> getInvalidFiles(int chr) {
        ArrayList<String> invalidMaps = new ArrayList<>();
        for (String filename : fileNames) {
            if (filename.charAt(0) == Character.forDigit(chr,10)) {
                invalidMaps.add(filename);
            }
        }
        return invalidMaps;
    }
}
