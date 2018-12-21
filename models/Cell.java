package models;

// マス目
public class Cell {
    public int x;
    public int y;
    // 画面で表示される文字
    public char character = '.';

    // マスが持ってる座標情報を更新
    public void setCoordinate(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public char getChar() {
        return character;
    }
}
