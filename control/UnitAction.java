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
        if(unit.isAlly()) {
            if(cell instanceof Unit)
                return false;
            else if(cell instanceof Mine){
                ((Mine) cell).bomb();
                new ExplodeAnimation().start(y2, x2);
                return true;
            }
        }

        // ユニット移動 (前にいたところは平地になる)
        Field.fieldmap[unit.y][unit.x] = new Flatland(unit.surroundMines, unit.isDetected);
        unit.setCoordinate(y2, x2);
        unit.surroundMines = cell.surroundMines;
        unit.isDetected = cell.isDetected;
        Field.fieldmap[y2][x2] = unit;

        if(unit.isAlly())
            detect();

        return true;
    }

    //敵を爆破
    public boolean detonate(int y, int x) {
        Cell cell = Field.fieldmap[y][x];
        if(cell instanceof Mine) {
            new ExplodeAnimation().start(y, x);
            ((Mine) cell).bomb();
        }

        return true;
    }

    // 周囲の平地の調査
    private void detect() {
        int[][] surround = surroundField(unit.y, unit.x);
        for(int i = 0; i < surround.length; i++) {
            Field.fieldmap[surround[i][0]][surround[i][1]].detected();
        }
    }
}
