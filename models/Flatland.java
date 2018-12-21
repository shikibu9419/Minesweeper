package models;

// 平地マス
public class Flatland extends Cell {
    public int surroundingBombs = 0;

    public Flatland() {}

    public Flatland(int num) {
        surroundingBombs = num;
        detected();
    }

    // その平地の周囲の地雷の数が調査された的なメソッド
    public void detected() {
        // int -> char のキャスト
        if(surroundingBombs > 0)
            character = (char)('0' + surroundingBombs);
    }
}
