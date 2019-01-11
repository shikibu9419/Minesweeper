package models;

// マス目のメインクラス
// 変数のディープコピーを行うためにimplementしている
public class Cell implements Cloneable {

    public int y;
    public int x;
    public int surroundingBombs = 0;  // 周りの地雷の数
    public String character = ".";  // 画面上で表示される文字

    public void setCoordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void decrementBombs() {
        surroundingBombs--;
        if(! character.equals("."))
            if(surroundingBombs == 0)
                character = ".";
            else
                character = String.valueOf(surroundingBombs);
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

    // Colors
    private String RED    = "\u001b[01;31m";
    private String GREEN  = "\u001b[01;32m";
    private String YELLOW = "\u001b[01;33m";
    private String BLUE   = "\u001b[01;34m";
    private String PINK   = "\u001b[01;35m";
    private String SKY    = "\u001b[01;36m";  // light blue
    private String WHITE  = "\u001b[01;37m";
    private String END    = "\u001b[00m";

    public void setCharacter(String character, String color) {
        String label = "";
        switch(color) {
            case "red":
                label = RED;
                break;
            case "green":
                label = GREEN;
                break;
            case "yellow":
                label = YELLOW;
                break;
            case "blue":
                label = BLUE;
                break;
            case "pink":
                label = PINK;
                break;
            case "sky":
                label = SKY;
                break;
            case "white":
                label = WHITE;
                break;
        }
        this.character = label + character + END;
    }
}
