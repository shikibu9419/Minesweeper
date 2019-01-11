package models;

// 平地マス
public class Flatland extends Cell {

    public boolean isDetected = false;

    public Flatland() {}

    public Flatland(int num) {
        surroundingBombs = num;
    }

    // その平地を調査した的なメソッド
    // (調査済みの平地は周囲の地雷の数が表示される)
    public void detected() {
        isDetected = true;
        if(surroundingBombs > 0)
            character = String.valueOf(surroundingBombs);
    }
}
