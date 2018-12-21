package system;

import models.*;

// ユニットの行動関連
public class UnitAction {
    private Unit unit;

    public UnitAction(Unit unit) {
        this.unit = unit;
    }

    // left/right/up/down のいずれかで移動する
    public void move(String direction) {
        int x2 = unit.x;
        int y2 = unit.y;
        switch(direction) {
            case "left":
                x2--;
                break;
            case "right":
                x2++;
                break;
            case "up":
                y2--;
                break;
            case "down":
                y2++;
                break;
            default:
                return;
        }

        // fieldの範囲外へは動かない
        if(Utils.outOfField(y2, x2))
            return;

        // 地雷踏んだらバーン
        if(Field.fieldmap[y2][x2] instanceof Mine) {
            ((Mine) Field.fieldmap[y2][x2]).bomb();
            return;
        }

        // ユニット移動
        Field.fieldmap[y2][x2] = unit;
        Field.fieldmap[unit.y][unit.x] = new Flatland();
        unit.setCoordinate(y2, x2);
    }
}

