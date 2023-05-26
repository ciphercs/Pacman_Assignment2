package src.utility;

import java.util.List;
import java.util.Objects;

public class Location {
    private int x;
    private int y;
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Location getLeft(){
        return new Location(this.getX() - 1, this.getY());
    }
    public Location getRight(){
        return new Location(this.getX() + 1, this.getY());
    }
    public Location getTop(){
        return new Location(this.getX(), this.getY() - 1);
    }
    public Location getBottom(){
        return new Location(this.getX(), this.getY() + 1);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void main(String[] args) {
        List<Location> list = List.of(new Location(2,8), new Location(4,10));
        System.out.println(new Location(2,7).getTop());
        System.out.println(new Location(2,9).getBottom());
        if(list.contains(new Location(2,8))){
            System.out.println("true");
        }

    }

}
