package levelcheck;

import src.utility.Location;
import src.utility.DataExtractor;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author jayaramannadurai
 * This class extended level rule and implements check rule method for gold and pill,
 * in such a way, if pill or gold is blocked it logs the error message.
 */

public class GoldPillAccessCheck extends levelcheck.LevelRule {
    private String resourceDir;
    public GoldPillAccessCheck(File directory) {
        super(directory);
        String dirPath = directory.toString();
        resourceDir = dirPath.substring(dirPath.lastIndexOf(System.getProperty("file.separator")) + 1, dirPath.length());
    }

    @Override
    public boolean checkRule() {
        boolean result = true;
        for (String fileName : fileNames) {
            Map<String, List<String>> data = DataExtractor.getData(System.getProperty("user.dir") + System.getProperty("file.separator") + resourceDir + System.getProperty("file.separator")+ fileName);
            List<String> pillLocation = data.get("pillTile");
            List<String> wallLocation = data.get("wallTile");
            // Converting pill string location into list of location objects and sorting it.
            List<Location> sortedPills = pillLocation.stream().map(s->{
                String[] temp = s.split(",");
                Location loc = new Location (Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                return loc;
            }).toList().stream().sorted(Comparator.comparingInt(Location::getY)).toList();

            // Converting wall string location into list of location objects and sorting it.
            List<Location> sortedWalls = wallLocation.stream().map(s->{
                String[] temp = s.split(",");
                Location loc = new Location (Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                return loc;
            }).toList().stream().sorted(Comparator.comparingInt(Location::getY)).toList();
            // Iterating all pills.
            for(Location pillLoc: sortedPills){
                // Checking if pills are surrounded by walls or not.
                if(isBlocked(pillLoc,sortedWalls)){
                    String msg = "[Level " + fileName + " - Pill not accessible: (" + pillLoc.getX() + "," + pillLoc.getY() + ")]" ;
                    this.errorMessage.add(msg);
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * This method finds if the passing location is surrounded by 3-sided walls or not.
     * @param pillLocation x,y location of object i.e Pill or Gold
     * @param wallLocation list of locations of wall
     * @return boolean value
     */
    public boolean isBlocked(Location pillLocation, List<Location> wallLocation){
        int count = 0;
        List<Location>adjLocations = List.of(pillLocation.getLeft(), pillLocation.getRight(), pillLocation.getTop(), pillLocation.getBottom());
        for(Location loc: adjLocations){
            if(wallLocation.contains(loc)){
                count++;
            }
        }
        return count > 2;
    }
}
