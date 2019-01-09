package models;

// マス目のメインクラス
// 変数のディープコピーを行うためにimplementしている
public class Cell implements Cloneable {

    public int y;
    public int x;
    public int surroundingBombs = 0;  // 周りの地雷の数
    public char character = '.';  // 画面上で表示される文字

    // マスが持ってる座標情報を更新
    public void setCoordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void decrementBombs() {
        surroundingBombs--;
        if(surroundingBombs == 0)
            character = '.';
    }

    // Cellオブジェクトのディープコピー
    public Cell clone() {
        Cell res = new Cell();
        try {
            res = (Cell)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return res;
    }
}
