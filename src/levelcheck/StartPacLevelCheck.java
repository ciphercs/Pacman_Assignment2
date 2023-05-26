package levelcheck;

import java.io.File;
import java.util.List;
import java.util.Map;
import src.utility.DataExtractor;

/**
 * @author jayaramannadurai
 * This class extended level rule and implements check rule method for pacMan,
 * in such a way, if pacMan does not have a starting point or there is more than one starting point it logs the error message.
 */

public class StartPacLevelCheck extends LevelRule {
    public StartPacLevelCheck(File directory) {
        super(directory);
    }

    @Override
    public boolean checkRule() {
        boolean result = true;
        Map<String, List<String>> data = DataExtractor.getData(directory);
        if (data.get("pacTile") == null) {
            String msg = "[Level " + directory.toString() + " – no start for PacMan ]";
            this.errorMessage.add(msg);
            result = false;
        }
        if(data.get("pacTile") != null && Integer.parseInt(data.get("pacCount").get(0)) > 1){
            String msg = "[Level " + directory.toString() + " – more than one start for Pacman: " + data.get("pacTile") + "]";
            this.errorMessage.add(msg);
            result = false;
        }
        return result;
    }
}
