package models;

// 平地マス
public class Flatland extends Cell {

    public Flatland() {}

    public Flatland(int num) {
        surroundingBombs = num;
    }

    public void decrementBombs() {
        surroundingBombs--;
        if(isDetected)
            detected();
    }
}
