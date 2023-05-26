package src;

import gamecheck.GameChecker;
import levelcheck.LevelChecker;
import matachi.mapeditor.editor.Controller;
import src.utility.GameCallback;

import java.io.File;

public class Driver {

    private static boolean stillAlive = true;

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
                LevelChecker levelChecker = new LevelChecker(map, logger);
                if (!gameChecker.checkGameRules() && !levelChecker.checkLevelRules()) {
                    new Controller();
                } else {
                    File[] maps = map.listFiles();
                    Controller player = new Controller();
                    for (File i: maps) {
                        if(i.getName().endsWith(".xml")){
                        if (!levelChecker.checkLevelRules()) {
                            if (stillAlive) {
                                player.openFile(i);
                                stillAlive = player.playGame(player.getMapString());
                            } else {
                                player.openEditor();
                                break;
                            }
                        } else {
                            stillAlive = false;
                            player.openFile(i);
                            break;
                        }
                    }
                    }
                    if (stillAlive){
                        new Controller();
                    }
                }
            } else {
                /*
                LevelChecker levelChecker = new LevelChecker(map, logger);
                if (!levelChecker.checkLevelRules()) {
                    Controller editor = new Controller();
                    editor.openFile(map);
                }

                 */
            }
        } else {
            new Controller();
        }
    }
}
