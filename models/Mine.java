package models;

import control.*;

// 地雷マス
public class Mine extends Cell {

    public Mine(int y, int x) {
        super(y, x);
        Field.fieldmap[y][x] = this;
    }

    public void bomb() {
        Field.fieldmap[y][x] = new Flatland(y, x, surroundMines, detected); // 爆発後は平地になる

        // 周囲は爆発に巻き込まれる
        int[][] surround = Control.surroundField(y, x);
        for(int i = 0; i < surround.length; i++) {
            Cell cell = Field.fieldmap[surround[i][0]][surround[i][1]];

            if(cell instanceof Flatland)
                ((Flatland) cell).decrementBombs();  // 平地は地雷の数情報が更新される
            else if(cell instanceof Mine)
                ((Mine) cell).bomb();   // 地雷は誘爆する
            else if(cell instanceof Unit)
                ((Unit) cell).death();  // 人は死ぬ
        }
    }
}
