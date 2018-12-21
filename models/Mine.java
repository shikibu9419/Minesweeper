package models;

import system.*;

// 地雷マス
public class Mine extends Cell {
    public Mine(int y, int x) {
        setCoordinate(y, x);
    }

    // Bomb!!!!!!!!!!!!!!!!!!!!!!
    public void bomb() {
        Field.fieldmap[this.y][this.x] = new Flatland();
        Field.fieldmap[this.y][this.x].character = 'X';

        // Surrounding field becomes Flatland.
        int[][] surround = Utils.surroundingField(y, x);
        for(int i = 0; i < surround.length; i++) {
            Cell cell = Field.fieldmap[surround[i][0]][surround[i][1]];

            // 平地は地雷の数が調整される
            if(cell instanceof Flatland)
                ((Flatland) cell).surroundingBombs--;
            // 地雷は誘爆する
            else if(cell instanceof Mine)
                ((Mine) cell).bomb();
            // 人は死ぬ
            else if(cell instanceof Unit)
                ((Unit) cell).death();

            cell.character = 'X';
        }
    }
}
