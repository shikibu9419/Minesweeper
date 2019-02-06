package control;

import java.util.*;
import models.*;
import options.UnitType;

// ステージ(fieldmap)の管理をするクラス
public class Field extends Information {

    private static Random rand = new Random();

    // Range: default
    public static Cell[] surrounds(Cell center) {
        return surrounds(center, 1, fieldmap);
    }

    // Range: specified
    public static Cell[] surrounds(Cell center, int range) {
        return surrounds(center, range, fieldmap);
    }

    // For only animations
    public static Cell[] surrounds(Cell center, Cell[][] map) {
        return surrounds(center, 1, map);
    }

    // 周囲rangeマス以内でfield範囲内のマス一覧を返す
    private static Cell[] surrounds(Cell center, int range, Cell[][] map) {
        ArrayList<Cell> cells = new ArrayList<>();

        for(int i = center.y - range; i <= center.y + range; i++)
            for(int j = center.x - range; j <= center.x + range; j++)
                if(! isOutOfField(i, j) && map[i][j] != center)
                    cells.add(map[i][j]);

        return cells.toArray(new Cell[cells.size()]);
    }

    public static boolean isOutOfField(int y, int x) {
        return (x < 0 || x >= MAX_X || y < 0 || y >= MAX_Y);
    }

    public static Cell[][] getClone() {
        Cell[][] clone = new Cell[MAX_Y][MAX_X];
        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                clone[i][j] = fieldmap[i][j].clone();
        return clone;
    }

    public static void updateAvailable(int y, int x, boolean available) {
        int range = AVAILABLE_RANGE + (available ? 0 : 1);
        fieldmap[y][x].available = available;

        for(Cell cell:surrounds(fieldmap[y][x], range))
            if(!(cell instanceof Unit))
                cell.available = available;
    }

    public static void initFieldmap() {
        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                new Flatland(i, j);

        setUnits(UnitType.ALLY);
        setUnits(UnitType.ENEMY);
        setMines();

        for(Unit ally:allies)
            new UnitAction(ally).detect();
        for(Unit enemy:enemies)
            new UnitAction(enemy).detect();
    }

    private static void setUnits(UnitType type) {
        Unit[] units = type.isAlly() ? allies : enemies;
        int count = 0;
        while(count < units.length) {
            int y = rand.nextInt(MAX_Y);
            int x = rand.nextInt(MAX_X);
            if(fieldmap[y][x] instanceof Unit)
                continue;

            units[count] = new Unit(y, x, type, count);
            count++;
        }
    }

    private static void setMines() {
        int count = 0;
        while(count < minesCount) {
            int y = rand.nextInt(MAX_Y);
            int x = rand.nextInt(MAX_X);

            if(! judgeMine(y, x))
                continue;

            Mine mine = new Mine(y, x);
            count++;

            // 地雷周辺の平地の地雷数をインクリメント
            for(Cell cell:surrounds(mine))
                cell.surroundMines++;
        }
    }

    // 地雷設置時の判定処理
    private static boolean judgeMine(int y, int x) {
        if(!(fieldmap[y][x] instanceof Flatland))
            return false;

        if(y - 1 > 0 && fieldmap[y - 1][x] instanceof Unit ||
           y + 1 < 0 && fieldmap[y + 1][x] instanceof Unit ||
           x - 1 > 0 && fieldmap[y][x - 1] instanceof Unit ||
           x + 1 < 0 && fieldmap[y][x + 1] instanceof Unit)
            return false;

        return true;
    }
}
