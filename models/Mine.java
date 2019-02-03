package models;

import control.Field;

// 地雷マス
public class Mine extends Cell {

    public Mine(int y, int x) {
        super(y, x);
    }

    public void bomb() {
        Field.fieldmap[y][x] = new Flatland(y, x, surroundMines, detected); // 爆発後は平地になる

        int[][] surround = Field.surroundField(y, x);
        for(int i = 0; i < surround.length; i++) {
            Cell cell = Field.fieldmap[surround[i][0]][surround[i][1]];

            if(cell instanceof Flatland)
                ((Flatland) cell).decrementBombs();
            else if(cell instanceof Mine)
                ((Mine) cell).bomb();
            else if(cell instanceof Unit)
                ((Unit) cell).death();
        }
        Field.minesCount--;
    }
}
