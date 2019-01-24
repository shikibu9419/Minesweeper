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
    public boolean available = false;  // ユニット選択中 or detonate にて選択可能か
    public boolean bombed = false;

    public Cell(int y, int x) {
        if(Information.outOfField(y, x))
            return;
        setCoordinate(y, x);
        Information.fieldmap[y][x] = this;
    }

    // 調査済みの平地は周囲の地雷の数が表示される
    public void detect() {
        detected = true;

        // TODO: surroundMinesの更新
        if(this instanceof Unit)
            return;
        else if(this instanceof Flatland && surroundMines > 0)
            character = String.valueOf(surroundMines);
        else
            character = ".";
    }

    public void setCoordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    // Cellオブジェクトのディープコピー
    public Cell clone() {
        Cell res = new Cell(-1, -1);  // 特に使用しないので負数を代入 (実装の敗北)
        try {
            res = (Cell)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return res;
    }
}
