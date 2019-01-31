package models;

import control.Information;
import configs.UnitType;

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

    // ユニット移動 (前にいたところは平地になる)
    public void moveTo(Cell dest) {
        Information.fieldmap[y][x] = new Flatland(y, x, surroundMines, detected);

        setCoordinate(dest.y, dest.x);
        surroundMines = dest.surroundMines;
        detected      = dest.detected;

        Information.fieldmap[dest.y][dest.x] = this;
    }

    // ユニットが死んだとき (暫定実装)
    public void death() {
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

    // 自分と周囲2マスのavailableを変更
    public void updateAvailable(boolean available) {
        int range = Information.AVAILABLE_RANGE;
        range += available ? 0 : 1;

        this.available = available;

        int[][] surround = Information.surroundField(y, x, range);
        for(int i = 0; i < surround.length; i++) {
            Cell cell = Information.fieldmap[surround[i][0]][surround[i][1]];
            if(!(cell instanceof Unit))
                cell.available = available;
        }
    }
}
