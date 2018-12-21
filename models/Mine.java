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

        // Surrounding field becomes Flatland.
        int[][] surround = Utils.surroundingField(y, x);
        for(int i = 0; i < surround.length; i++) {
            Cell cell = Field.fieldmap[surround[i][0]][surround[i][1]];

            if(cell instanceof Flatland)
                // 平地は地雷の数が調整される
                ((Flatland) cell).surroundingBombs--;
            else if(cell instanceof Mine)
                // 地雷は誘爆する
                ((Mine) cell).bomb();
            else if(cell instanceof Unit)
                // 人は死ぬ
                ((Unit) cell).death();
        }
    }

    public char getChar() {
        return 'X';
    }
}
