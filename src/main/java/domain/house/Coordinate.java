package domain.house;

//This class serves as a basic model for representing coordinates in a two-dimensional space.
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
//This class, Coordinate, represents a basic model for storing coordinates in a two-dimensional space.
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
