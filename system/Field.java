package system;

import models.*;
import java.util.*;

// ステージ(field)の管理をするクラス
public class Field {
    // ゲームにつき1つのためクラス変数で管理する子たち
    public static final int MAX_X = 20;
    public static final int MAX_Y = 20;
    public static final int MINE_COUNT = 25;
    public static Cell[][] fieldmap;
    public static Unit unit;

    // fieldの初期化
    public static void initFieldmap() {
        fieldmap = new Cell[MAX_Y][MAX_X];

        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                fieldmap[i][j] = new Flatland();

        // 主人公の配置
        unit = new Unit();
        fieldmap[0][0] = unit;
        unit.setCoordinate(0, 0);

        // 地雷の配置
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        while(count < MINE_COUNT) {
            Random rand = new Random();
            int x = rand.nextInt(MAX_X);
            int y = rand.nextInt(MAX_Y);

            // 平地でなかったら飛ばす
            if(!(fieldmap[y][x] instanceof Flatland))
                continue;

            // 地雷を設置
            fieldmap[y][x] = new Mine();
            count++;

            // 地雷周辺の平地に地雷の数を設定する
            for(int j = 0; j < 8; j++) {
                int x2 = x + dx[j];
                int y2 = y + dy[j];

                // はみ出す & スタートorゴール & 平地じゃない ときは考えない
                if(x2 < 0 || x2 >= MAX_X || y2 < 0 || y2 >= MAX_Y ||
                   x2 == 0 && y2 == 0 || x2 == MAX_X - 1 && y2 == MAX_Y - 1 ||
                   !(fieldmap[y2][x2] instanceof Flatland))
                    continue;

                ((Flatland) fieldmap[y2][x2]).surroundingBombs++;
            }
        }
    }
}
