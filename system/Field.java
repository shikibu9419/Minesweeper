package system;

import models.*;

// ステージ(field)の管理をするクラス
public class Field {
    // ゲームにつき1つのためクラス変数で管理する子たち
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static final int MINE_COUNT = 50;
    public static Cell[][] fieldmap = new Cell[MAX_Y][MAX_X];
    // 操作可能ユニット
    public static Unit unit = new Unit();

    // fieldの初期化
    public static void initFieldmap() {
        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                fieldmap[i][j] = new Flatland();

        // 主人公の配置
        fieldmap[0][0] = unit;
        unit.setCoordinate(0, 0);

        // 地雷の配置
        int count = 0;
        while(count < MINE_COUNT) {
            int y = Utils.randomInt(MAX_Y);
            int x = Utils.randomInt(MAX_X);

            // スタート周辺orゴールor平地でない ときは飛ばす
            if((y < 2 && x < 2) ||
               (y == MAX_Y - 1 && x == MAX_X - 1) ||
               !(fieldmap[y][x] instanceof Flatland))
                continue;

            // 地雷を設置
            Mine mine = new Mine(y, x);
            fieldmap[y][x] = mine;
            count++;

            // 地雷周辺の平地に地雷の数を設定する
            int[][] surround = Utils.surroundingField(y, x);
            for(int i = 0; i < surround.length; i++) {
                int y2 = surround[i][0];
                int x2 = surround[i][1];

                // 平地でないときは飛ばす
                if(!(fieldmap[y2][x2] instanceof Flatland))
                    continue;

                // 地雷数情報をインクリメント
                ((Flatland) fieldmap[y2][x2]).surroundingBombs++;
            }
        }
    }
}
