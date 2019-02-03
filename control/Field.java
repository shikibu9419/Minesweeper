package control;

import java.util.*;
import models.*;
import options.UnitType;

// ステージ(fieldmap)の管理をするクラス
public class Field extends Information {

    private static Random rand = new Random();

    // 周囲rangeマスのfield範囲内の座標一覧を{{y, x}, {{y, x}, ...}の配列にして返す
    public static int[][] surroundField(int y, int x, int range) {
        ArrayList<int[]> surround = new ArrayList<>();

        for(int i = y - range; i <= y + range; i++) {
            for(int j = x - range; j <= x + range; j++) {
                if(isOutOfField(i, j))
                    continue;
                int[] yx = {i, j};
                surround.add(yx);
            }
        }

        int[][] res = new int[surround.size()][2];
        for(int i = 0; i < surround.size(); i++)
            res[i] = surround.get(i);

        return res;
    }

    public static int[][] surroundField(int y, int x) {
        return surroundField(y, x, 1);
    }

    public static boolean isOutOfField(int y, int x) {
        return (x < 0 || x >= Field.MAX_X || y < 0 || y >= Field.MAX_Y);
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

        int[][] surround = surroundField(y, x, range);
        for(int i = 0; i < surround.length; i++) {
            Cell cell = fieldmap[surround[i][0]][surround[i][1]];
            if(!(cell instanceof Unit))
                cell.available = available;
        }
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
            int[][] surround = surroundField(y, x);
            for(int i = 0; i < surround.length; i++)
                fieldmap[surround[i][0]][surround[i][1]].surroundMines++;
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
