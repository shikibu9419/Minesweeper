package control;

import models.*;
import ui.animations.*;

// ユニットの行動関連
public class UnitAction extends Control {

    private Unit unit;
    private Cell[][] fieldmap = Information.fieldmap;

    public UnitAction(Unit unit) {
        this.unit = unit;
    }

    // left/right/up/down のいずれかに移動する
    public boolean move(String direction) {
        if(disabled())
            return false;

        int y2 = unit.y;
        int x2 = unit.x;
        switch(direction) {
            case "w":
                x2--;
                break;
            case "e":
                x2++;
                break;
            case "n":
                y2--;
                break;
            case "s":
                y2++;
                break;
            default:
                return false;
        }

        // fieldの範囲外へは移動しない
        if(outOfField(y2, x2))
            return false;

        Cell cell = fieldmap[y2][x2];

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
        fieldmap[unit.y][unit.x] = new Flatland(unit.surroundMines, unit.detected);
        unit.setCoordinate(y2, x2);
        unit.surroundMines = cell.surroundMines;
        unit.detected = cell.detected;
        fieldmap[y2][x2] = unit;

        if(unit.isAlly())
            detect();

        notice(String.format("Unit %s moved to (%d, %d).", unit.character, unit.x + 1, unit.y + 1));
        return true;
    }

    //敵を爆破
    public boolean detonate(int y, int x) {
        if(disabled())
            return false;

        Cell cell = fieldmap[y][x];
        if(cell instanceof Mine) {
            new ExplodeAnimation().start(y, x);
            ((Mine) cell).bomb();
            notice(String.format("Mine on (%d, %d) bombed.", x + 1, y + 1));
        } else
            notice(String.format("There is no mine on (%d, %d).", x + 1, y + 1));

        return true;
    }

    public void cancel() {
        notice("Selection Unit " + unit.character + " canceled.");
    }

    // 周囲の平地の調査
    private void detect() {
        int[][] surround = surroundField(unit.y, unit.x);
        for(int i = 0; i < surround.length; i++) {
            fieldmap[surround[i][0]][surround[i][1]].detect();
        }
    }

    private boolean disabled() {
        if(unit.dead) {
            notice("Unit " + unit.character + " is Dead.");
            return true;
        }
        if(unit.acted) {
            notice("Unit " + unit.character + " acted.");
            return true;
        }
        return (unit.dead || unit.acted) ? true : false;
    }

    private void notice(String msg) {
        Information.addNotification(msg);
    }
}
