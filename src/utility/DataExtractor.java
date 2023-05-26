package src.utility;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jayaramannadurai
 *
 * Description: This class reads xml file with pre-define structure
 *              and converts the data into java collection that is map.
 *
 */
public class DataExtractor {

    /**
     *
     * This method fetch the data from given xml file
     * and it returns map with main tag data in the form of list.
     * @param fileWithDir :file name with directory that is absolute path
     * @return Map with key as for eg. pacTile and value as list of Location data
     *
     */
    public static Map<String,List<String>> getData(String fileWithDir) {
        SAXBuilder builder = new SAXBuilder();
        Map<String,List<String>> locationMap = new LinkedHashMap<>();
        try {
            BufferedReader in;
            FileReader reader = null;
            File currentFile = new File(fileWithDir);

            Document document;
                if (currentFile.canRead() && currentFile.exists()) {
                    document = (Document) builder.build(currentFile);

                    Element rootNode = document.getRootElement();

                    List sizeList = rootNode.getChildren("size");
                    Element sizeElem = (Element) sizeList.get(0);
                    int height = Integer.parseInt(sizeElem
                            .getChildText("height"));
                    int width = Integer
                            .parseInt(sizeElem.getChildText("width"));
                    locationMap.put("width", List.of(String.valueOf(width)));
                    locationMap.put("height", List.of(String.valueOf(height)));
                    List rows = rootNode.getChildren("row");

                    for (int y = 0; y < rows.size(); y++) {
                        Element cellsElem = (Element) rows.get(y);
                        List cells = cellsElem.getChildren("cell");

                        for (int x = 0; x < cells.size(); x++) {
                            Element cell = (Element) cells.get(x);
                            String cellValue = cell.getText();


                            if (cellValue.equals("PathTile")) {

                                if (locationMap.get("pathTile") == null) {
                                    List<String> loc  = new ArrayList<>();
                                    loc.add(x + "," + y);
                                    locationMap.put("pathTile", loc);
                                }else{
                                    List<String> temp = locationMap.get("pathTile");
                                    temp.add(x + "," + y);
                                    locationMap.put("pathTile", temp);
                                }
                            }else if (cellValue.equals("WallTile")) {

                                if (locationMap.get("wallTile") == null) {
                                    List<String> loc  = new ArrayList<>();
                                    loc.add(x + "," + y);
                                    locationMap.put("wallTile", loc);
                                }else{
                                    List<String> temp = locationMap.get("wallTile");
                                    temp.add(x + "," + y);
                                    locationMap.put("wallTile", temp);
                                }
                            }else if (cellValue.equals("PillTile")) {

                                if (locationMap.get("pillTile") == null) {
                                    List<String> count  = new ArrayList<>();
                                    List<String> loc  = new ArrayList<>();
                                    count.add("1");
                                    loc.add(x + "," + y);
                                    locationMap.put("pillTile", loc);
                                    locationMap.put("pillTileCount", count);
                                }else{
                                    List<String> temp = locationMap.get("pillTile");
                                    List<String> tempCount = locationMap.get("pillTileCount");
                                    temp.add(x + "," + y);
                                    tempCount.add("" + Integer.parseInt(tempCount.get(0)) + 1);
                                    locationMap.put("pillTile", temp);
                                    locationMap.put("pillTileCount", tempCount);
                                }
                            }else if (cellValue.equals("GoldTile")) {

                                if (locationMap.get("goldTile") == null) {
                                    List<String> count  = new ArrayList<>();
                                    List<String> loc  = new ArrayList<>();
                                    count.add("1");
                                    loc.add(x + "," + y);
                                    locationMap.put("goldTileCount", count);
                                    locationMap.put("goldTile", loc);
                                }else{
                                    List<String> temp = locationMap.get("goldTile");
                                    List<String> tempCount = locationMap.get("goldTileCount");
                                    temp.add(x + "," + y);
                                    tempCount.add("" + Integer.parseInt(tempCount.get(0)) + 1);
                                    locationMap.put("goldTileCount", tempCount);
                                    locationMap.put("goldTile", temp);
                                }
                            }else if (cellValue.equals("IceTile")) {

                                if (locationMap.get("iceTile") == null) {
                                    List<String> loc  = new ArrayList<>();
                                    loc.add(x + "," + y);
                                    locationMap.put("iceTile", loc);
                                }else{
                                    List<String> temp = locationMap.get("iceTile");
                                    temp.add(x + "," + y);
                                    locationMap.put("iceTile", temp);
                                }
                            }else if (cellValue.equals("PacTile")) {

                                if (locationMap.get("pacTile") == null) {
                                    List<String> count  = new ArrayList<>();
                                    List<String> loc  = new ArrayList<>();
                                    count.add("1");
                                    loc.add(x + "," + y);
                                    locationMap.put("pacTile", loc);
                                    locationMap.put("pacCount", count);

                                }else{
                                    List<String> tempCount = locationMap.get("pacCount");
                                    List<String> temp = locationMap.get("pacTile");
                                    temp.add(x + "," + y);
                                    tempCount.add("" + Integer.parseInt(tempCount.get(0)) + 1);
                                    locationMap.put("pacTile", temp);
                                    locationMap.put("pacCount", tempCount);
                                }
                            }else if (cellValue.equals("TrollTile")) {

                                if (locationMap.get("trollTile") == null) {
                                    List<String> loc  = new ArrayList<>();
                                    loc.add(x + "," + y);
                                    locationMap.put("trollTile", loc);
                                }else{
                                    List<String> temp = locationMap.get("trollTile");
                                    temp.add(x + "," + y);
                                    locationMap.put("trollTile", temp);
                                }
                            }else if (cellValue.equals("TX5Tile")) {

                                if (locationMap.get("tx5Tile") == null) {
                                    List<String> loc  = new ArrayList<>();
                                    loc.add(x + "," + y);
                                    locationMap.put("tx5Tile", loc);
                                }else{
                                    List<String> temp = locationMap.get("tx5Tile");
                                    temp.add(x + "," + y);
                                    locationMap.put("tx5Tile", temp);
                                }
                            }else if (cellValue.equals("PortalWhiteTile")) {

                                if (locationMap.get("portalWhiteTile") == null) {
                                    List<String> count  = new ArrayList<>();
                                    count.add("1");
                                    List<String> loc  = new ArrayList<>();
                                    loc.add(x + "," + y);
                                    locationMap.put("portalWhiteTile", loc);
                                    locationMap.put("portalWhiteTileCount", count);

                                }else{
                                    List<String> tempCount = locationMap.get("portalWhiteTileCount");
                                    List<String> temp = locationMap.get("portalWhiteTile");
                                    tempCount.add("" + Integer.parseInt(tempCount.get(0)) + 1);
                                    temp.add(x + "," + y);
                                    locationMap.put("portalWhiteTile", temp);
                                    locationMap.put("portalWhiteTileCount", tempCount);

                                }
                            }else if (cellValue.equals("PortalYellowTile")) {

                                if (locationMap.get("portalYellowTile") == null) {
                                    List<String> count  = new ArrayList<>();
                                    List<String> loc  = new ArrayList<>();
                                    count.add("1");
                                    loc.add(x + "," + y);
                                    locationMap.put("portalYellowTile", loc);
                                    locationMap.put("portalYellowTileCount", count);

                                }else{
                                    List<String> tempCount = locationMap.get("portalYellowTileCount");
                                    List<String> temp = locationMap.get("portalYellowTile");
                                    tempCount.add("" + Integer.parseInt(tempCount.get(0)) + 1);
                                    temp.add(x + "," + y);
                                    locationMap.put("portalYellowTileCount", tempCount);
                                    locationMap.put("portalYellowTile", temp);
                                }
                            }else if (cellValue.equals("PortalDarkGoldTile")) {

                                if (locationMap.get("portalDarkGoldTile") == null) {
                                    List<String> count  = new ArrayList<>();
                                    List<String> loc  = new ArrayList<>();
                                    count.add("1");
                                    loc.add(x + "," + y);
                                    locationMap.put("portalDarkGoldTileCount", count);
                                    locationMap.put("portalDarkGoldTile", loc);
                                }else{
                                    List<String> tempCount = locationMap.get("portalDarkGoldTileCount");
                                    List<String> temp = locationMap.get("portalDarkGoldTile");
                                    tempCount.add("" + Integer.parseInt(tempCount.get(0)) + 1);
                                    temp.add(x + "," + y);
                                    locationMap.put("portalDarkGoldTileCount", tempCount);
                                    locationMap.put("portalDarkGoldTile", temp);
                                }
                            }else if (cellValue.equals("PortalDarkGrayTile")) {

                                if (locationMap.get("portalDarkGrayTile") == null) {
                                    List<String> count  = new ArrayList<>();
                                    List<String> loc  = new ArrayList<>();
                                    count.add("1");
                                    loc.add(x + "," + y);
                                    locationMap.put("portalDarkGrayTileCount", count);
                                    locationMap.put("portalDarkGrayTile", loc);
                                }else{
                                    List<String> tempCount = locationMap.get("portalDarkGrayTileCount");
                                    List<String> temp = locationMap.get("portalDarkGrayTile");
                                    tempCount.add("" + Integer.parseInt(tempCount.get(0)) + 1);
                                    temp.add(x + "," + y);
                                    locationMap.put("portalDarkGrayTileCount", tempCount);
                                    locationMap.put("portalDarkGrayTile", temp);
                                }
                            }else
                                System.out.println("Invalid condition");
                        }
                    }
                    System.out.println("Location" + locationMap);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationMap;
    }

}
