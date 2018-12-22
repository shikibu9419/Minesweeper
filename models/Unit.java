package models;

import system.*;

// 動かす駒
public class Unit extends Cell {
    public boolean isDead = false;

    public Unit(int y, int x) {
        character = 'o';
        setCoordinate(y, x);
    }

    public void death() {
        Field.fieldmap[y][x] = new Flatland();
        Field.fieldmap[y][x].character = 'X';
        this.isDead = true;
    }
}
