package models;

// 平地マス
public class Flatland extends Cell {

    public Flatland() {}

    public Flatland(int num) {
        surroundingBombs = num;
        detected();
    }

    // その平地を調査した的なメソッド
    // (調査済みの平地は周囲の地雷の数が表示される)
    public void detected() {
        if(surroundingBombs > 0)
            character = (char)('0' + surroundingBombs);
    }
}
