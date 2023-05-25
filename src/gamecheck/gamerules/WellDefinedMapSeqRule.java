package gamecheck.gamerules;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WellDefinedMapSeqRule extends GameRule {
    private ArrayList<Integer> firstChars;
    private HashMap<String, Integer> filenameToNum = new HashMap<>();

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
                characterFound.set(chr-1, true);
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
            String num = "";
            for (Character c : filename.toCharArray()) {
                if (Character.isDigit(c)) {
                    num += c;
                } else {
                    break;
                }
            }
            arr.add(Integer.parseInt(num));
            this.filenameToNum.put(filename, Integer.parseInt(num));
        }
        return arr;
    }

    private ArrayList<String> getInvalidFiles(int num) {
        ArrayList<String> invalidMaps = new ArrayList<>();
        for (String filename : fileNames) {
            if (this.filenameToNum.get(filename) == num) {
                invalidMaps.add(filename);
            }
        }
        return invalidMaps;
    }
}
