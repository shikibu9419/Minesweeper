package models;

import control.Information;

// マス目のメインクラス
// 変数のディープコピーを行うためにimplementしている
public class Cell implements Cloneable {

    public int y;
    public int x;
    public int surroundMines = 0;      // 周りの地雷の数
    public String character  = ".";    // 画面上で表示される文字
    public boolean detected  = false;  // 調査済か
    public boolean available = false;  // ユニット選択中 / detonateにて選択可能か
    public boolean bombed    = false;  // 爆発済か (control.Opponent専用)

    public Cell(int y, int x) {
        if(Information.isOutOfField(y, x))
            return;
        setCoordinate(y, x);
        Information.fieldmap[y][x] = this;
    }

    // 調査済みの平地は周囲の地雷の数が表示される
    public void detect() {
        detected = true;
        if(! (this instanceof Unit))
            character = (this instanceof Flatland && surroundMines > 0) ? String.valueOf(surroundMines) : ".";
    }

    public void setCoordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    // Cellオブジェクトのディープコピー
    public Cell clone() {
        Cell res = new Cell(-1, -1);
        try {
            res = (Cell)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return res;
    }
}
