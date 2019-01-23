package control;

import models.*;

// ステージ(field)の管理をするクラス
public class Field extends Information {

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

        setUnits();
        setMines();

        for(Unit ally:allies)
            new UnitAction(ally).detect();
    }

    // ユニットの設定 (暫定)
    private static void setUnits() {
        int count = 0;
        while(count < allies.length) {
            int y = randomInt(MAX_Y);
            int x = randomInt(MAX_X);
            if(fieldmap[y][x] instanceof Unit)
                continue;

            allies[count] = new Unit(y, x, count, "ally");
            count++;
        }

        count = 0;
        while(count < enemies.length) {
            int y = randomInt(MAX_Y);
            int x = randomInt(MAX_X);
            if(fieldmap[y][x] instanceof Unit)
                continue;

            enemies[count] = new Unit(y, x, count, "enemy");
            count++;
        }
    }

    // 地雷の設定
    private static void setMines() {
        int count = 0;
        while(count < MAX_MINES) {
            int y = randomInt(MAX_Y);
            int x = randomInt(MAX_X);

            // スタート周辺orゴールor平地でない ときは飛ばす
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

    private static  boolean judgeMine(int y, int x) {
        if(!(fieldmap[y][x] instanceof Flatland))
            return false;

        int[][] surround = surroundField(y, x);
        for(int i = 0; i < surround.length; i++)
            if(fieldmap[surround[i][0]][surround[i][1]] instanceof Unit)
                return false;

        return true;
    }
}
