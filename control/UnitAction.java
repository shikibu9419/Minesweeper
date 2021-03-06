package control;

import models.*;
import ui.animations.ExplodeAnimation;

// ユニットの行動関連
public class UnitAction extends Field {

    private Unit unit;

    public UnitAction(Unit unit) {
        this.unit = unit;
    }

    // left/right/up/down のいずれかに移動する
    public boolean move(String direction) {
        int y2 = unit.y;
        int x2 = unit.x;
        switch(direction) {
            case "a":
                x2--;
                break;
            case "d":
                x2++;
                break;
            case "w":
                y2--;
                break;
            case "s":
                y2++;
                break;
            default:
                return false;
        }

        if(isOutOfField(y2, x2))
            return false;

        Cell cell = fieldmap[y2][x2];

        if(cell instanceof Unit)
            return false;
        else if(cell instanceof Mine){
            new ExplodeAnimation().start(y2, x2);
            ((Mine) cell).bomb();
            return true;
        }

        unit.moveTo(cell);

        detect();

        noticeAction(String.format("Moved to (%d, %d).", unit.x + 1, unit.y + 1));

        return true;
    }

    public boolean detonate(int y, int x) {
        if(isOutOfField(y, x)) {
            noticeAction(String.format("(%d, %d) is out of field!", x + 1, y + 1));
            return false;
        }

        Cell cell = fieldmap[y][x];
        if(cell.available == false) {
            noticeAction(String.format("Can't select (%d, %d).", x + 1, y + 1));
            return false;
        }

        if(cell instanceof Mine) {
            new ExplodeAnimation().start(y, x);
            ((Mine) cell).bomb();
            noticeAction(String.format("Exploded on (%d, %d).", x + 1, y + 1));
        } else
            noticeAction(String.format("There is no mine on (%d, %d).", x + 1, y + 1));

        return true;
    }

    public void cancel() {
        noticeAction("Selection canceled.");
    }

    public void detect() {
        Cell[] cells = surrounds(unit);
        for(Cell cell:cells)
            cell.detect();
    }

    private void noticeAction(String msg) {
        addNotification(String.format("%s: %s", unit.character, msg));
    }
}
