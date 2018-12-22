package models;

import system.*;

// 動かす駒
public class Unit extends Cell {
    public boolean isDead = false;

    public Unit(int y, int x) {
        setCoordinate(y, x);
        Field.fieldmap[y][x] = this;
        character = 'o';
    }

    public void death() {
        Field.fieldmap[y][x] = new Flatland();
        Field.fieldmap[y][x].character = 'X';
        this.isDead = true;
    }
}
