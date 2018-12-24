package models;

import control.*;

// 地雷マス
public class Mine extends Cell {

    public Mine(int y, int x) {
        setCoordinate(y, x);
        Field.fieldmap[y][x] = this;
    }

    // Bomb!!!!!!!!!!!!!!!!!!!!!!
    public void bomb() {
        Field.fieldmap[y][x] = new Flatland();

        // Surrounding field becomes Flatland.
        int[][] surround = Control.surroundingField(y, x);
        for(int i = 0; i < surround.length; i++) {
            Cell cell = Field.fieldmap[surround[i][0]][surround[i][1]];

            // 平地は地雷の数情報が調整される
            if(cell instanceof Flatland)
                ((Flatland) cell).surroundingBombs--;
            // 地雷は誘爆する
            else if(cell instanceof Mine)
                ((Mine) cell).bomb();
            // 人は死ぬ
            else if(cell instanceof Unit)
                ((Unit) cell).death();
        }
    }

//     public void detected() {
//         // int -> char のキャスト
//         if(surroundingBombs > 0)
//             character = (char)('0' + surroundingBombs);
//     }
}
