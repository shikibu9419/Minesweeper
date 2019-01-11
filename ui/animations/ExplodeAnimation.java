package ui.animations;

import control.*;
import models.Mine;
import java.util.*;

// 爆発アニメーション
public class ExplodeAnimation extends Animation {

    private Queue<int[]> queue = new ArrayDeque<>();

    public ExplodeAnimation() {
        super();
    }

    public void start(int y, int x) {
        int[] yx = {y, x};
        queue.add(yx);

        // 幅優先爆発
        while(!queue.isEmpty()) {
            yx = queue.remove();
            y = yx[0];
            x = yx[1];

            // explode済みの地雷は飛ばす
            if(fieldmap[y][x].character.matches(".*\\*.*"))
                continue;

            explode(y, x);
            sleep(5); // sleep 0.5s
        }
    }

    // 爆発に巻き込まれたところを*で表示
    private void explode(int y, int x) {
        fieldmap[y][x].setCharacter("*", "yellow");

        int[][] surround = Control.surroundingField(y, x);
        for(int i = 0; i < surround.length; i++) {
            int y2 = surround[i][0];
            int x2 = surround[i][1];

            // 周囲の地雷以外のマスの文字を*に変更
            // (地雷マスはqueueに追加)
            if(!(fieldmap[y2][x2] instanceof Mine))
                fieldmap[y2][x2].setCharacter("*", "yellow");
            else
                queue.add(surround[i]);
        }

        displayField(fieldmap);
    }
}
