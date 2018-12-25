package ui.animations;

import control.Control;
import models.Mine;
import java.util.*;

// 爆発アニメーション
public class ExplodeAnimation extends Animation {

    public ExplodeAnimation() {
        super();
    }

    // 爆発に巻き込まれたところをXで表示
    public void start(int y, int x) {
        int[] yx = {y, x};
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.add(yx);

        // 幅優先爆発
        while(!queue.isEmpty()) {
            // 変数の使い回し (よくない)
            yx = queue.remove();
            y = yx[0];
            x = yx[1];

            explode(y, x);
            sleep(5);

            int[][] surround = Control.surroundingField(y, x);
            for(int i = 0; i < surround.length; i++) {
                // まだ爆発指定ない地雷のセット
                if(fieldmap[surround[i][0]][surround[i][1]] instanceof Mine &&
                   fieldmap[surround[i][0]][surround[i][1]].character != '*')
                    queue.add(surround[i]);
            }
        }
    }

    private void explode(int y, int x) {
        // 周囲の地雷以外と自分の文字を*に変更
        int[][] surround = Control.surroundingField(y, x);
        fieldmap[y][x].character = '*';
        for(int i = 0; i < surround.length; i++) {
            int y2 = surround[i][0];
            int x2 = surround[i][1];
            if(!(fieldmap[y2][x2] instanceof Mine))
                fieldmap[y2][x2].character = '*';
        }

        displayField(fieldmap);
    }
}
