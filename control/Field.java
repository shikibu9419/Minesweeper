package control;

import java.util.Random;
import models.*;
import configs.UnitType;

// ステージ(field)の管理をするクラス
public class Field extends Information {

    private static Random rand = new Random();

    // 本体に影響がないようにfieldmapのディープコピーを渡すメソッド
    public static Cell[][] getClone() {
        Cell[][] res = new Cell[MAX_Y][MAX_X];
        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                res[i][j] = fieldmap[i][j].clone();
        return res;
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

    // 地雷の設定
    private static void setMines() {
        int count = 0;
        while(count < minesCount) {
            int y = rand.nextInt(MAX_Y);
            int x = rand.nextInt(MAX_X);

            if(! judgeMine(y, x))
                continue;

            Mine mine = new Mine(y, x);  // 地雷を設置
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

        if((y - 1 > 0 && fieldmap[y - 1][x] instanceof Unit) ||
           (y + 1 < 0 && fieldmap[y + 1][x] instanceof Unit) ||
           (x - 1 > 0 && fieldmap[y][x - 1] instanceof Unit) ||
           (x + 1 < 0 && fieldmap[y][x + 1] instanceof Unit))
            return false;

        return true;
    }
}
