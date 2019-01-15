package models;

// 平地マス
public class Flatland extends Cell {

    public Flatland(int y, int x) {
        super(y, x);
    }

    public Flatland(int y, int x, int num, boolean detected) {
        super(y, x);
        this.surroundMines = num;
        if(detected)
            detect();
    }

    public void decrementBombs() {
        surroundMines--;
        if(detected)
            detect();
    }
}
