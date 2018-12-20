package system;

// 平地マス
public class Flatfield extends Cell {
    int surroundingBombs = 0;

    public char getChar() {
        return '.';
    }
}
