package models;

// マス目
public abstract class Cell {
    public int x;
    public int y;

    // マスが持ってる座標情報を更新
    public void setCoordinate(int y, int x) {
        this.x = x;
        this.y = y;
    }

    // 表示上での文字を返す
    public abstract char getChar();
}
