package control;

import models.*;

// ステージ(field)の管理をするクラス
public class Field extends Information {

    public static Cell[][] fieldmap = new Cell[MAX_Y][MAX_X];

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
                fieldmap[i][j] = new Flatland();

        setUnits();
        setMines();
    }

    // ユニットの設定 (暫定)
    private static void setUnits() {
        for(int i = 0; i < allies.length; i++) {
            int y = randomInt(3);
            int x = randomInt(3);
            if(fieldmap[y][x] instanceof Unit){
                i--;
                continue;
            }

            allies[i] = new Unit(y, x, i, "ally");
            fieldmap[y][x] = allies[i];
        }

        for(int i = 0; i < enemies.length; i++) {
            int y = MAX_Y - 1 - randomInt(3);
            int x = MAX_X - 1 - randomInt(3);
            if(fieldmap[y][x] instanceof Unit){
                i--;
                continue;
            }

            enemies[i] = new Unit(y, x, -1, "enemy");
            fieldmap[y][x] = enemies[i];
        }
    }

    // 地雷の設定
    private static void setMines() {
        int count = 0;
        while(count < MINE_COUNT) {
            int y = randomInt(MAX_Y);
            int x = randomInt(MAX_X);

            // スタート周辺orゴールor平地でない ときは飛ばす
            if((y < 4 && x < 4) || (y == MAX_Y - 4 && x == MAX_X - 4) || !(fieldmap[y][x] instanceof Flatland))
                continue;

            Mine mine = new Mine(y, x);  // 地雷を設置
            count++;

            // 地雷周辺の平地の地雷数をインクリメント
            int[][] surround = surroundField(y, x);
            for(int i = 0; i < surround.length; i++) {
                fieldmap[surround[i][0]][surround[i][1]].surroundMines++;
            }
        }
    }
}
