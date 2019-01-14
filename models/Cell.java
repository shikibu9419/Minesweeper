package models;

// マス目のメインクラス
// 変数のディープコピーを行うためにimplementしている
public class Cell implements Cloneable {

    public int y;
    public int x;
    public int surroundMines = 0;       // 周りの地雷の数
    public boolean isDetected = false;  // 調査されたことがあるか
    public String character = ".";      // 画面上で表示される文字
    public String color = "";           //  とその色

    // 調査済みの平地は周囲の地雷の数が表示される
    public void detected() {
        isDetected = true;

        if(this instanceof Unit)
            return;
        else if(this instanceof Flatland && surroundMines > 0)
            setLooks(String.valueOf(surroundMines), "green");
        else
            setLooks(".", "green");
    }

    public void setCoordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void setLooks(String character, String color) {
        this.character = character;
        this.color = color;
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
