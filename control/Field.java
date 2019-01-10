package control;

import models.*;

// ステージ(field)の管理をするクラス
public class Field extends Control {

    // ゲームにつき1つのためクラス変数で管理する子たち
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static final int MINE_COUNT = 50;
    public static Cell[][] fieldmap = new Cell[MAX_Y][MAX_X];

    private static final int ALLIES_COUNT = 3;
    private static final int ENEMIES_COUNT = 3;
    public static Unit[] allies = new Unit[ALLIES_COUNT];
    public static Unit[] enemies = new Unit[ENEMIES_COUNT];

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
        for(int i = 0; i < ALLIES_COUNT; i++) {
            allies[i] = new Unit(i, i, "ally");
            Field.fieldmap[i][i] = allies[i];
        }

        for(int i = 0; i < ENEMIES_COUNT; i++) {
            int hoge = MAX_X - i - 1;
            enemies[i] = new Unit(hoge, hoge, "enemy");
            Field.fieldmap[hoge][hoge] = enemies[i];
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
            int[][] surround = surroundingField(y, x);
            for(int i = 0; i < surround.length; i++) {
                fieldmap[surround[i][0]][surround[i][1]].surroundingBombs++;
            }
        }
    }
}
