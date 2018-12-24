package control;

import models.*;

// ユニットの行動関連
public class UnitAction extends Control {
    private Unit unit;

    public UnitAction(Unit unit) {
        this.unit = unit;
    }

    // left/right/up/down のいずれかで移動する
    public void move(String direction) {
        int y2 = unit.y;
        int x2 = unit.x;
        switch(direction) {
            case "l":
                x2--;
                break;
            case "r":
                x2++;
                break;
            case "u":
                y2--;
                break;
            case "d":
                y2++;
                break;
            default:
                return;
        }

        // fieldの範囲外へは動かない
        if(outOfField(y2, x2))
            return;

        Cell cell = Field.fieldmap[y2][x2];
        // 地雷踏んだらバーン
        if(cell instanceof Mine) {
//             new ExplodeAnimation(y2, x2).start();
            ((Mine) cell).bomb();
            return;
        }

        // ユニット移動
        Field.fieldmap[y2][x2] = unit;
        Field.fieldmap[unit.y][unit.x] = new Flatland(unit.surroundingBombs);
        unit.setCoordinate(y2, x2);
        detect();

        // 平地だったら周囲の地雷情報を受け継ぐ
        if(cell instanceof Flatland)
            unit.surroundingBombs = ((Flatland) cell).surroundingBombs;
    }

    // 周囲の平地の調査
    private void detect() {
        int[][] surround = surroundingField(unit.y, unit.x);
        for(int i = 0; i < surround.length; i++) {
//             Field.fieldmap[surround[i][0]][surround[i][1]].detected();
            Cell cell = Field.fieldmap[surround[i][0]][surround[i][1]];
            if(cell instanceof Flatland)
                ((Flatland) cell).detected();
        }
    }
}

