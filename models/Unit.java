package models;

import control.*;

// 動かす駒
public class Unit extends Cell {

    private static final String[] CHARACTERS = {"A", "B", "C", "D", "E"};
    public String type;
    public boolean dead  = false;
    public boolean acted = false;

    public Unit(int y, int x, int index, String type) {
        super(y, x);
        this.type = type;
        if(isAlly()) {
            character = CHARACTERS[index];
            detected  = true;
        } else {
            character = "X";
            detected  = false;
        }
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
        return type.equals("ally");
    }

    // 自分と周囲2マスのavailableを変更
    public void updateAvailable(boolean available) {
        int range = Information.AVAILABLE_RANGE;
        range += available ? 0 : 1;

        this.available = available;

        for(int i = y - range; i < y + range + 1; i++) {
            for(int j = x - range; j < x + range + 1; j++) {
                if(Information.outOfField(i, j) || Information.fieldmap[i][j] instanceof Unit)
                    continue;
                Information.fieldmap[i][j].available = available;
            }
        }
    }
}
