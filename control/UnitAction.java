package control;

import models.*;
import ui.animations.*;

// ユニットの行動関連
public class UnitAction extends Control {

    private Unit unit;

    public UnitAction(Unit unit) {
        this.unit = unit;
    }

    // left/right/up/down のいずれかに移動する
    public boolean move(String direction) {
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
                return false;
        }

        // fieldの範囲外へは移動しない
        if(outOfField(y2, x2))
            return false;

        Cell cell = Field.fieldmap[y2][x2];

        // 地形ごとの設定
        if(cell instanceof Unit)
            return false;
        else if(cell instanceof Flatland)
            unit.surroundingBombs = ((Flatland) cell).surroundingBombs;
        else {
            new ExplodeAnimation().start(y2, x2);
            ((Mine) cell).bomb();
            return true;
        }

        // ユニット移動 (前にいたところは平地になる)
        Field.fieldmap[y2][x2] = unit;
        Field.fieldmap[unit.y][unit.x] = new Flatland(unit.surroundingBombs);
        unit.setCoordinate(y2, x2);
        detect();

        return true;
    }

    //敵を爆破
    public boolean detonate(int y, int x) {
        new ExplodeAnimation().start(y, x);

        Cell cell = Field.fieldmap[y][x];
        if(cell instanceof Mine)
            ((Mine) cell).bomb();

        return true;
    }

    // 周囲の平地の調査
    private void detect() {
        int[][] surround = surroundingField(unit.y, unit.x);
        for(int i = 0; i < surround.length; i++) {
            Cell cell = Field.fieldmap[surround[i][0]][surround[i][1]];
            if(cell instanceof Flatland)
                ((Flatland) cell).detected();
        }
    }
}
