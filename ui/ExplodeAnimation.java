package ui;

import control.Control;
import models.Mine;

// 爆発アニメーション
public class ExplodeAnimation extends Animation {

    public ExplodeAnimation() {
        super();
    }

    // 爆発に巻き込まれたところをXで表示
    public void start(int y, int x) {
        fieldmap[y][x].character = 'X';

        // 周囲の地雷以外の文字をXに変更
        int[][] surround = Control.surroundingField(y, x);
        for(int i = 0; i < surround.length; i++) {
            int y2 = surround[i][0];
            int x2 = surround[i][1];
            if(!(fieldmap[y2][x2] instanceof Mine))
                fieldmap[y2][x2].character = 'X';
        }

        // 表示, 1秒待機
        displayField(fieldmap);
        sleep(1);

        // 地雷の誘爆
        for(int i = 0; i < surround.length; i++) {
            int y2 = surround[i][0];
            int x2 = surround[i][1];
            if(fieldmap[y2][x2] instanceof Mine && fieldmap[y2][x2].character != 'X')
                start(y2, x2);
        }
    }
}
