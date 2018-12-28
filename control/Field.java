package control;

import models.*;

// ステージ(field)の管理をするクラス
public class Field extends Control {

    // ゲームにつき1つのためクラス変数で管理する子たち
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static final int MINE_COUNT = 75;
    public static Cell[][] fieldmap = new Cell[MAX_Y][MAX_X];
    public static Unit unit;

    // fieldの初期化
    public static void initFieldmap() {
        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                fieldmap[i][j] = new Flatland();

        unit = new Unit(0, 0);  // 操作可能ユニットを配置

        // ランダムな場所に地雷を配置
        int count = 0;
        while(count < MINE_COUNT) {
            int y = randomInt(MAX_Y);
            int x = randomInt(MAX_X);

            // スタート周辺orゴールor平地でない ときは飛ばす
            if((y < 2 && x < 2) ||
               (y == MAX_Y - 1 && x == MAX_X - 1) ||
               !(fieldmap[y][x] instanceof Flatland))
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
