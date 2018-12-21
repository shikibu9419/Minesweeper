package models;

import system.*;

// 動かす駒
public class Unit extends Cell {
    public int surroundingBombs = 0;
    public boolean isDead = false;

    public void death() {
        Field.fieldmap[y][x] = new Flatland();
        this.isDead = true;
    }

    public char getChar() {
        return 'o';
    }
}
