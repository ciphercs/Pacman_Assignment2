package levelcheck;
import src.utility.DataExtractor;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author jayaramannadurai
 * This class extended level rule and implements check rule method for gold and pill,
 * in such a way, if sum of pill and gold count is less than two it logs the error message.
 */

public class GoldPillLevelCheck extends LevelRule {
    //private String resourceDir;
    public GoldPillLevelCheck(File directory) {
        super(directory);
        //String dirPath = directory.toString();
        //resourceDir = dirPath.substring(dirPath.lastIndexOf(System.getProperty("file.separator")) + 1, dirPath.length());
    }

    @Override
    public boolean checkRule() {
        boolean result = true;
        for (String fileName : fileNames) {
            Map<String, List<String>> data = DataExtractor.getData(System.getProperty("user.dir") + System.getProperty("file.separator") +  System.getProperty("file.separator")+ fileName);

            int goldCount = 0;
            int pillCount = 0;

            if (data.containsKey("goldTileCount")){
                goldCount = data.get("goldTileCount").size();
            }

            if (data.containsKey("pillTileCount")){
                pillCount = data.get("pillTileCount").size();
            }

            if (goldCount + pillCount < 2) {
                String msg = "[Level " + fileName + " – less than 2 Gold and Pill]";
                this.errorMessage.add(msg);
                result = false;
            }


        }
        return result;
    }
}
