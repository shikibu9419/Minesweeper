package models;

import control.*;

// 動かす駒
public class Unit extends Cell {

    private static String[] allyChars = {"A", "B", "C"};
    public boolean isDead = false;
    public String type;

    public Unit(int y, int x, int index, String type) {
        setCoordinate(y, x);
        this.type = type;
        if(isAlly())
            setCharacter(allyChars[index], "blue");
        else
            setCharacter("X", "red");
    }

    // ユニットが死んだとき (暫定実装)
    public void death() {
        decrementBombs();
        Field.fieldmap[y][x] = new Flatland(this.surroundingBombs);
        this.isDead = true;
        if(type.equals("ally"))
            Field.allies_count--;
        else
            Field.enemies_count--;
    }

    public boolean isAlly() {
        return type.equals("ally");
    }
}
