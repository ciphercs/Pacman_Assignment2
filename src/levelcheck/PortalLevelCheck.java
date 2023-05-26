package levelcheck;
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
    public PortalLevelCheck(File directory) {
        super(directory);
    }

    @Override
    public boolean checkRule() {
        boolean result = true;
        Map<String, List<String>> data = DataExtractor.getData(directory);

        if (data.get("portalWhiteTile") != null && data.get("portalWhiteTileCount").size() != 2) {
            String msg = "[Level " + directory.toString() + " - portal White count is not 2:" + data.get("portalWhiteTile") + "]";
            this.errorMessage.add(msg);
            result = false;
        }
        if (data.get("portalYellowTile") != null && data.get("portalYellowTileCount").size() != 2) {
            String msg = "[Level " + directory.toString() + " - portal Yellow count is not 2:" + data.get("portalYellowTile") + "]";
            this.errorMessage.add(msg);
            result = false;
        }
        if (data.get("portalDarkGoldTile") != null && data.get("portalDarkGoldTileCount").size() != 2) {
            String msg = "[Level " + directory.toString() + " - portal Dark Gold count is not 2:" + data.get("portalDarkGoldTile") + "]";
            this.errorMessage.add(msg);
            result = false;
        }
        if (data.get("portalDarkGrayTile") != null && data.get("portalDarkGrayTileCount").size() != 2) {
            String msg = "[Level " + directory.toString() + " - portal Dark Gray count is not 2:" + data.get("portalDarkGrayTile") + "]";
            this.errorMessage.add(msg);
            result = false;
        }
        return result;
    }
}
