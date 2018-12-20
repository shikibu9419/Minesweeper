package models;

// 1つ1つの駒
public class Unit extends Cell {
    public int x;
    public int y;

    public void setCoordinate(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public char getChar() {
        return 'o';
    }
}
