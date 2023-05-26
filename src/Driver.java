package src;

import gamecheck.GameChecker;
import levelcheck.LevelChecker;
import matachi.mapeditor.editor.Controller;
import src.utility.GameCallback;

import java.io.File;
import java.util.logging.Level;

public class Driver {

    private static boolean stillAlive = true;
    private static boolean errorMap = false;

    /**
     * Starting point
     * @param args the command line arguments
     *
     */

    public static void main(String args[]) {
        src.utility.GameCallback logger = new GameCallback();
        if (args.length > 0){
            File map = new File(args[0]);
            if (map.isDirectory()){
                GameChecker gameChecker = new GameChecker(map);
                if (!gameChecker.checkGameRules()) {
                    new Controller();
                } else {
                    File[] maps = map.listFiles();
                    Controller player = new Controller();
                    for (File i: maps) {
                        if (stillAlive){
                            LevelChecker levelChecker = new LevelChecker(i, logger);
                            if (levelChecker.checkLevelRules()){
                                player.openFile(i);
                                stillAlive = player.playGame(player.getMapString());
                            } else {
                                player.openFile(i);
                                errorMap = true;
                                break;
                            }
                        }
                    }
                    if(!errorMap){
                        new Controller();
                    }
                }
            } else {
                LevelChecker levelChecker = new LevelChecker(map, logger);
                levelChecker.checkLevelRules();
                Controller editor = new Controller();
                editor.openFile(map);
            }
        } else {
            new Controller();
        }
    }
}
