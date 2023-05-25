package src.autoplayerstrategies;

import ch.aplu.jgamegrid.Location;
import java.util.ArrayList;

public interface MovementStrategy {
    public ArrayList<Location> moveSequence();
}
