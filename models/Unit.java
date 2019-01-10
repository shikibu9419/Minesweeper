package models;

import control.Field;

// 動かす駒
public class Unit extends Cell {

    public boolean isDead = false;
    public String type;

    public Unit(int y, int x, String type) {
        setCoordinate(y, x);
        this.character = 'o';
        this.type = type;
    }

    // ユニットが死んだとき (暫定実装)
    public void death() {
        decrementBombs();
        Field.fieldmap[y][x] = new Flatland(this.surroundingBombs);
        this.isDead = true;
    }
}
