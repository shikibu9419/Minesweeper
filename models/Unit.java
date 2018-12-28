package models;

import control.Field;

// 動かす駒
public class Unit extends Cell {

    public boolean isDead = false;

    public Unit(int y, int x) {
        setCoordinate(y, x);
        Field.fieldmap[y][x] = this;
        character = 'o';
    }

    // ユニットが死んだとき (暫定実装)
    public void death() {
        Field.fieldmap[y][x] = new Flatland();
        Field.fieldmap[y][x].character = 'X';
        this.isDead = true;
    }
}
