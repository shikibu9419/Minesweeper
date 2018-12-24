package models;

// 平地マス
public class Flatland extends Cell {
    public Flatland() {}

    public Flatland(int num) {
        surroundingBombs = num;
        detected();
    }

    // その平地周辺を調査した的なメソッド
    public void detected() {
        // int -> char のキャスト
        if(surroundingBombs > 0)
            character = (char)('0' + surroundingBombs);
    }
}
