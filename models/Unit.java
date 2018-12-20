package models;

// 動かす駒
public class Unit extends Cell {
    public int x;
    public int y;

    // ユニットが持ってる座標情報を更新
    public void setCoordinate(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public char getChar() {
        return 'o';
    }
}
