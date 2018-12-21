package models;

// マス目
public class Cell {
    // 座標 ([y][x]で格納されている)
    public int y;
    public int x;
    // 画面上で表示される文字
    public char character = '.';

    // マスが持ってる座標情報を更新
    public void setCoordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public char getChar() {
        return character;
    }
}
