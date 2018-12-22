package models;

// 平地マス
public class Flatland extends Cell {
    public Flatland() {}

    public Flatland(int num) {
        surroundingBombs = num;
        detected();
    }

    public void detected() {
        // int -> char のキャスト
        if(surroundingBombs > 0)
            character = (char)('0' + surroundingBombs);
    }
}
