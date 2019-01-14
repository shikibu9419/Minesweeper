package models;

// 平地マス
public class Flatland extends Cell {

    public Flatland() {}

    public Flatland(int num, boolean isDetected) {
        this.surroundMines = num;
        if(isDetected)
            detected();
    }

    public void decrementBombs() {
        surroundMines--;
        if(isDetected)
            detected();
    }
}
