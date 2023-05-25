package src.autoplayerstrategies;

import ch.aplu.jgamegrid.Location;
import src.Game;
import src.PacManGameGrid;

import java.util.ArrayList;

// For level checking:
// "getPath" gives a path between from and to, and returns an empty list if there is no path
// so, the level checking fails if there isnt a path between starting position and each gold and pill
// the hardest thing i think would be to rewrite the "getPortalLocations" method, since right now its in the game
// I don't think you otherwise need to do anything other than initialise nbVert, nbHorz and grid
// You can use getAccessibleLocations to initialise the accessibleLocations array
// pillLocations are the locations of the gold and pills, but the getPath doesn't use it
// i might be wrong though, just let me know if you need help

public class NearestPill implements MovementStrategy {
    private Location currentLocation;
    private Game game;
    private ArrayList<Location> pillLocations;
    private ArrayList<Location> accessibleLocations;
    private int nbVert;
    private int nbHorz;
    private PacManGameGrid grid;

    public NearestPill(Location curr, Game game) {
        this.currentLocation = curr;
        this.game = game;
        this.pillLocations = game.getCurrentPillAndGoldLocations();
        this.nbVert = game.getNumVertCells();
        this.nbHorz = game.getNumHorzCells();
        this.grid = game.getGrid();
        this.accessibleLocations = getAccessibleLocations();
    }

    @Override
    public ArrayList<Location> moveSequence() {
        Location to = closestPillLocationTo(this.currentLocation);
        ArrayList<Location> path = getPath(currentLocation, to);
        System.out.println("path: " + path);
        return path;
    }

    // find a path through the maze to 2 points in the maze
    // uses Dijkstra's algorithm
    // mimics pseudocode at https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    private ArrayList<Location> getPath(Location from, Location to) {
        System.out.println("from: " + from + ", to: " + to);
        ArrayList<Location> S = new ArrayList<>();
        int dist[][] = new int[nbVert][nbHorz];
        Location prev[][] = new Location[nbVert][nbHorz];
        ArrayList<Location> queue = new ArrayList<>();
        for (int y=0; y<nbVert; y++) {
            for (int x=0; x<nbHorz; x++) {
                if (!accessibleLocations.contains(new Location(x,y))) {
                    dist[y][x] = -1;
                } else {
                    dist[y][x] = 1000;
                    queue.add(new Location(x,y));
                }
            }
        }
        put(from, dist, 0);

        while (queue.size() != 0) {
            Location u = getLocWithMinDist(dist, queue);
            if (u.equals(to)) {
                if (get(prev, u) != null || u.equals(from)) {
                    while (u != null) {
                        S.add(0, u);
                        u = get(prev, u);
                    }
                }
                break;
            }
            queue.remove(u);
            ArrayList<Location> neighboursInQueue = getNeighboursInQueue(queue, u);
            System.out.println("neighboursInQueue: " + neighboursInQueue);
            for (Location v : neighboursInQueue) {
                int alt = dist[u.getY()][u.getX()] + 1;
                if (alt < get(dist, v)) {
                    put(v, dist, alt);
                    put(v, prev, u);
                }
            }
        }
        S.remove(0);
        return S;
    }

    private Location closestPillLocationTo(Location curr) {
        int currentDistance = 1000;
        Location closestLocation = null;
        for (Location loc : this.pillLocations) {
            int distanceToPill = loc.getDistanceTo(this.currentLocation);
            if (distanceToPill < currentDistance) {
                closestLocation = loc;
                currentDistance = distanceToPill;
            }
        }
        return closestLocation;
    }

    // gets adjacent locations, considering the portals
    private ArrayList<Location> adjacentLocations(Location loc) {
        ArrayList<Location> trueAdjacent = new ArrayList<>();
        int x = loc.x;
        int y = loc.y;
        trueAdjacent.add(new Location(x, y-1));
        trueAdjacent.add(new Location(x-1, y));
        trueAdjacent.add(new Location(x+1, y));
        trueAdjacent.add(new Location(x, y+1));

        ArrayList<Location> portalAdjacent = new ArrayList<>();
        for (Location adjLocation : trueAdjacent) {
            if (isOutOfBounds(adjLocation)) {
                continue;
            }
            int a = grid.getCell(adjLocation);
            if (a == 5) { // white portal
                Location newLoc = game.getPortalLocations("white", adjLocation);
                System.out.println("newLoc: " + newLoc);
                portalAdjacent.add(newLoc);
            } else if (a == 6) {
                Location newLoc = game.getPortalLocations("yellow", adjLocation);
                portalAdjacent.add(newLoc);
            } else if (a == 7) {
                Location newLoc = game.getPortalLocations("darkGold", adjLocation);
                portalAdjacent.add(newLoc);
            } else if (a == 8) {
                Location newLoc = game.getPortalLocations("darkGray", adjLocation);
                portalAdjacent.add(newLoc);
            } else {
                portalAdjacent.add(adjLocation);
            }
        }
        return portalAdjacent;
    }

    private void put(Location loc, int[][] dist, int val) {
        int x = loc.getX();
        int y = loc.getY();
        dist[y][x] = val;
    }
    private void put(Location loc, Location[][] prev, Location newLoc) {
        int x = loc.getX();
        int y = loc.getY();
        prev[y][x] = newLoc;
    }

    private int get(int[][] dist, Location loc) {
        int x = loc.x;
        int y = loc.y;
        return dist[y][x];
    }
    private Location get(Location[][] prev, Location loc) {
        int x = loc.x;
        int y = loc.y;
        return prev[y][x];
    }

    private Location getLocWithMinDist(int[][] dist, ArrayList<Location> queue){
        int minDist = 10000;
        Location minLoc = new Location(100,100);
        for (int y=0; y<nbVert; y++) {
            for (int x=0; x<nbHorz; x++) {
                int elem = dist[y][x];
                Location loc = new Location(x,y);
                if (elem >= 0 && queue.contains(loc)) {
                    if (elem < minDist) {
                        minDist = elem;
                        minLoc = loc;
                    }
                }
            }
        }
        return minLoc;
    }

    private ArrayList<Location> getNeighboursInQueue(ArrayList<Location> queue, Location loc) {
        ArrayList<Location> adjLocations = adjacentLocations(loc);
        System.out.println("adjLocations: " + adjLocations + " adjacent to " + loc);
        ArrayList<Location> nhbInQueue = new ArrayList<>();
        for (Location l : adjLocations) {
            if (queue.contains(l) && adjLocations.contains(l)) {
                nhbInQueue.add(l);
            }
        }
        return nhbInQueue;
    }

    private boolean isOutOfBounds(Location loc) {
        int x = loc.getX();
        int y = loc.getY();

        if (x<0 || x>=nbHorz || y<0 || y>=nbVert) {
            return true;
        }
        return false;
    }

    private ArrayList<Location> getAccessibleLocations() {
        ArrayList<Location> accessibleLoc = new ArrayList<>();
        for (int x=0; x<nbHorz; x++) {
                for (int y=0; y<nbVert; y++) {
                Location loc = new Location(x,y);
                if (grid.getCell(loc) != 0) {
                    accessibleLoc.add(loc);
                }
            }
        }
        return accessibleLoc;
    }
}
