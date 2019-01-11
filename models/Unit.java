package models;

import control.*;

// 動かす駒
public class Unit extends Cell {

    private static String[] allyChars = {"A", "B", "C"};
    public String type;
    public boolean isDead = false;

    public Unit(int y, int x, int index, String type) {
        isDetected = true;
        setCoordinate(y, x);
        this.type = type;
        if(isAlly())
            setLooks(allyChars[index], "green");
        else
            setLooks("X", "red");
    }

    // ユニットが死んだとき (暫定実装)
    public void death() {
        Field.fieldmap[y][x] = new Flatland(this.surroundMines, isDetected);
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
