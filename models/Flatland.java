package models;

// 平地マス
public class Flatland extends Cell {

    public Flatland() {}

    public Flatland(int num, boolean detected) {
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
