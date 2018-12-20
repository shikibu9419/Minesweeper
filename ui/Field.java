package ui;

import system.*;
import java.util.*;

// ステージの管理をするクラス
public class Field {
    public final int MAX_X = 20;
    public final int MAX_Y = 20;
    public final int MINE_COUNT = 20;
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
                fieldmap[i][j] = new Flatfield();

        // 主人公の配置
        fieldmap[0][0] = new Unit();

        // 地雷の配置
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for(int i = 0; i < MINE_COUNT; i++) {
            int x = random(MAX_X);
            int y = random(MAX_Y);

            if(fieldmap[y][x] instanceof Flatfield)
                fieldmap[y][x] = new Minefield();
            else {
                i--;
                continue;
            }

            // 地雷周辺の平地に地雷の数を設定
            for(int j = 0; j < 8; j++) {
                int x2 = x + dx[j];
                int y2 = y + dy[j];

                if(x2 < 0 || x2 >= MAX_X || y2 < 0 || y2 >= MAX_Y)
                    continue;
                if(x2 == 0 && y2 == 0 || x2 == MAX_X - 1 && y2 == MAX_Y - 1)
                    continue;
                if(!(fieldmap[y2][x2] instanceof Flatfield))
                    continue;

                ((Flatfield) fieldmap[y2][x2]).surroundingBombs++;
            }
        }
    }

    private int random(int MAX) {
        return rand.nextInt(MAX);
    }
}
