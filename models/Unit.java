package models;

import control.Information;
import options.UnitType;

// 動かす駒
public class Unit extends Cell {

    private static final String[] CHARACTERS = {"A", "B", "C"};

    public UnitType type;
    public boolean  dead  = false;
    public boolean  acted = false;

    public Unit(int y, int x, UnitType type, int index) {
        super(y, x);
        this.type = type;
        this.character = (type.isAlly() || Information.mode.isPvP()) ? CHARACTERS[index] : "X";
        detected  = true;
    }

    public void moveTo(Cell dest) {
        Information.fieldmap[y][x] = new Flatland(y, x, surroundMines, detected);

        setCoordinate(dest.y, dest.x);
        surroundMines = dest.surroundMines;
        detected      = dest.detected;

        Information.fieldmap[dest.y][dest.x] = this;
    }

    public void death() {
        if(dead)
            return;

        Information.fieldmap[y][x] = new Flatland(y, x, surroundMines - 1, detected);
        dead = true;
        if(isAlly())
            Information.alliesCount--;
        else
            Information.enemiesCount--;
        Information.addNotification(String.format("%s: Died.", this.character));
    }

    public boolean isAlly() {
        return type.isAlly();
    }
}
