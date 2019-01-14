package models;

import control.Information;

// 動かす駒
public class Unit extends Cell {

    private static String[] allyChars = {"A", "B", "C"};
    public String type;
    public boolean dead  = false;
    public boolean acted = false;

    public Unit(int y, int x, int index, String type) {
        detected = true;
        setCoordinate(y, x);
        this.type = type;
        if(isAlly())
            character = allyChars[index];
        else
            character = "X";
    }

    // ユニットが死んだとき (暫定実装)
    public void death() {
        Information.fieldmap[y][x] = new Flatland(this.surroundMines - 1, detected);
        this.dead = true;
        if(type.equals("ally"))
            Information.allies_count--;
        else
            Information.enemies_count--;
    }

    public boolean isAlly() {
        return type.equals("ally");
    }

    public void updateAvailable(boolean available) {
        this.available = available;

        for(int i = y - 3; i < y + 3; i++) {
            for(int j = x - 3; j < x + 3; j++) {
                if(Information.outOfField(i, j))
                    continue;
                if(Information.fieldmap[i][j] instanceof Unit)
                    continue;

                Information.fieldmap[i][j].available = available;
            }
        }
    }
}
