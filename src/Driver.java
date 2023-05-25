package src;

import gamecheck.GameChecker;
import matachi.mapeditor.editor.Controller;
import gamecheck.*;

import java.io.File;
import java.lang.reflect.Array;

public class Driver {
//    public static final String DEFAULT_PROPERTIES_PATH = "properties/test1.properties";

    /**
     * Starting point
     * @param args the command line arguments
     */

    public static void main(String args[]) {
//        String propertiesPath = DEFAULT_PROPERTIES_PATH;
////        if (args.length > 0) {
////            propertiesPath = args[0];
////        }
////        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
////        GameCallback gameCallback = new GameCallback();
        if (args.length > 0){
            File map = new File(args[0]);
            if (map.isDirectory()){
                GameChecker gameChecker = new GameChecker(map);
                if (!gameChecker.checkGameRules()) {
                    new Controller();
                } else {
                    File[] maps = map.listFiles();
                    Controller player = new Controller();
                    player.openFile(maps[maps.length - 1]);
                    player.playGame(player.getMapString());
                }
            } else {
                //TODO levelCheck
                Controller editor = new Controller();
                editor.openFile(map);
            }
        } else {
            new Controller();
        }
//        new pacgame.Game(gameCallback, properties);
    }
}
