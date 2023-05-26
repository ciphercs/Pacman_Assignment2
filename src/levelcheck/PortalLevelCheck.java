package src.levelcheck;
import src.utility.DataExtractor;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author jayaramannadurai
 * This class extended level rule and implements check rule method for portal,
 * in such a way, if white, yellow, dark gold or dark gray portals do not have complete pair it logs the error message.
 */

public class PortalLevelCheck extends LevelRule {
    private String resourceDir;
    public PortalLevelCheck(File directory) {
        super(directory);
        String dirPath = directory.toString();
        resourceDir = dirPath.substring(dirPath.lastIndexOf(System.getProperty("file.separator")) + 1, dirPath.length());
    }

    @Override
    public boolean checkRule() {
        boolean result = true;
        for (String fileName : fileNames) {
            Map<String, List<String>> data = DataExtractor.getData(System.getProperty("user.dir") + System.getProperty("file.separator") + resourceDir + System.getProperty("file.separator")+ fileName);

            if (data.get("portalWhiteTile") != null && Integer.parseInt(data.get("portalWhiteTileCount").get(0)) != 2) {
                String msg = "[Level " + fileName + " - portal White count is not 2:" + data.get("portalWhiteTile") + "]";
                this.errorMessage.add(msg);
                result = false;
            }
            if (data.get("portalYellowTile") != null && Integer.parseInt(data.get("portalYellowTileCount").get(0)) != 2) {
                String msg = "[Level " + fileName + " - portal Yellow count is not 2:" + data.get("portalYellowTile") + "]";
                this.errorMessage.add(msg);
                result = false;
            }
            if (data.get("portalDarkGoldTile") != null && Integer.parseInt(data.get("portalDarkGoldTileCount").get(0)) != 2) {
                String msg = "[Level " + fileName + " - portal Dark Gold count is not 2:" + data.get("portalDarkGoldTile") + "]";
                this.errorMessage.add(msg);
                result = false;
            }
            if (data.get("portalDarkGrayTile") != null && Integer.parseInt(data.get("portalDarkGrayTileCount").get(0)) != 2) {
                String msg = "[Level " + fileName + " - portal Dark Gray count is not 2:" + data.get("portalDarkGrayTile") + "]";
                this.errorMessage.add(msg);
                result = false;
            }

        }
        return result;
    }
}
