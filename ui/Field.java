package ui;

import system.*;
import java.util.*;

// ステージの管理をするクラス
public class Field {
    public final int MAX_X = 20;
    public final int MAX_Y = 20;
    public final int MINE_COUNT = 25;
    public final Random rand;
    public Cell[][] fieldmap;

    public Field() {
        rand = new Random();
        fieldmap = new Cell[MAX_Y][MAX_X];
        init();
    }

    private void init() {
        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                fieldmap[i][j] = new Flatland();

        // 主人公の配置
        fieldmap[0][0] = new Unit();

        // 地雷の配置
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        while(count < MINE_COUNT) {
            int x = random(MAX_X);
            int y = random(MAX_Y);

            // 平地でなかったら飛ばす
            if(!(fieldmap[y][x] instanceof Flatland))
                continue;

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

    // お手軽にランダム
    private int random(int MAX) {
        return rand.nextInt(MAX);
    }
}
