package models;

import system.*;

// 動かす駒
public class Unit extends Cell {
    public int surroundingBombs;
    public boolean isDead = false;

    public Unit() {
        character = 'o';
    }

    public void death() {
        Field.fieldmap[y][x] = new Flatland();
        this.isDead = true;
    }
}
