package src;

import ch.aplu.jgamegrid.Location;
import src.autoplayerstrategies.NearestPill;

import java.util.ArrayList;

public class Autoplayer {
    private ArrayList<Location> path = new ArrayList<>();

    // makes a decision about the next move the autoplayer wants to make, based on current state of the game
    public Location nextMove(Location currentLocation, Game game) {
        Location next;

        if (!path.isEmpty()) {
            next = path.get(0);
            path.remove(0);
            return next;
        }

        NearestPill nearestPillStrategy = new NearestPill(currentLocation, game);
        path = nearestPillStrategy.moveSequence();
        next = path.get(0);
        path.remove(0);
        return next;
    }
}
