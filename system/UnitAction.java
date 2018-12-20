package system;

import models.*;
import java.util.*;

// ユニットの行動関連
public class UnitAction {
    private Unit unit;

    public UnitAction(Unit unit) {
        this.unit = unit;
    }

    // left, right, up, down のいずれかで移動
    public void move(char direction) {
        int x2 = unit.x;
        int y2 = unit.y;
        switch(direction) {
            case 'l':
                x2--;
                break;
            case 'r':
                x2++;
                break;
            case 'u':
                y2--;
                break;
            case 'd':
                y2++;
                break;
            default:
                return;
        }

        // fieldの範囲内だと移動が成立
        if(x2 >= 0 && x2 < Field.MAX_X && y2 >= 0 && y2 < Field.MAX_Y) {
            Field.fieldmap[y2][x2] = unit;
            Field.fieldmap[unit.y][unit.x] = new Flatland();
            unit.setCoordinate(y2, x2);
        }
    }
}

