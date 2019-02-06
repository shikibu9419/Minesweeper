package control;

import java.util.*;
import models.*;
import options.UnitType;

// ステージ(fieldmap)の管理をするクラス
public class Field extends Information {

    private static Random rand = new Random();

    // 周囲rangeマス以内でfield範囲内のマス一覧返す
    public static Cell[] surroundCells(Cell center, int range, Cell[][] fieldmap) {
        ArrayList<Cell> cells = new ArrayList<>();

        for(int i = center.y - range; i <= center.y + range; i++)
            for(int j = center.x - range; j <= center.x + range; j++)
                if(! isOutOfField(i, j) && fieldmap[i][j] != center)
                    cells.add(fieldmap[i][j]);

//         Cell[] res = new Cell[cells.size()];
//         cells.toArray(res);

        return cells.toArray(new Cell[cells.size()]);
    }

    public static Cell[] surroundCells(Cell center) {
        return surroundCells(center, 1, fieldmap);
    }

    public static Cell[] surroundCells(Cell center, int range) {
        return surroundCells(center, range, fieldmap);
    }

    // For animations
    public static Cell[] surroundCells(Cell center, Cell[][] map) {
        return surroundCells(center, 1, map);
    }

    public static boolean isOutOfField(int y, int x) {
        return (x < 0 || x >= MAX_X || y < 0 || y >= MAX_Y);
    }

    public static Cell[][] getClone() {
        Cell[][] res = new Cell[MAX_Y][MAX_X];
        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                res[i][j] = fieldmap[i][j].clone();
        return res;
    }

    public static void updateAvailable(int y, int x, boolean available) {
        int range = AVAILABLE_RANGE + (available ? 0 : 1);
        fieldmap[y][x].available = available;

        for(Cell cell:surroundCells(fieldmap[y][x], range))
            if(!(cell instanceof Unit))
                cell.available = available;
    }

    // fieldの初期化
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
            for(Cell cell:surroundCells(mine))
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
