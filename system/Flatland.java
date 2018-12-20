package system;

// 平地マス
public class Flatland extends Cell {
    public int surroundingBombs = 0;

    public char getChar() {
        // int -> char のキャスト
        return surroundingBombs > 0 ? (char)('0' + surroundingBombs) : '.';
    }
}
