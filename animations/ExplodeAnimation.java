package animations;

import control.*;
import models.*;
import java.util.*;

// 爆発アニメーション
public class ExplodeAnimation extends Animation {

    private List<Queue<int[]>> queue = new ArrayList<>();
    private int enqueued = 0, dequeued = 1;

    public ExplodeAnimation() {
        super();
        queue.add(new ArrayDeque<int[]>());
        queue.add(new ArrayDeque<int[]>());
    }

    public void start(int y, int x) {
        int[] yx = {y, x};
        queue.get(enqueued).add(yx);

        // 幅優先爆発
        while(! queue.get(enqueued).isEmpty()) {
            enqueued = (enqueued + 1) % 2;
            dequeued = (dequeued + 1) % 2;

            while(! queue.get(dequeued).isEmpty()) {
                yx = queue.get(dequeued).remove();
                y = yx[0];
                x = yx[1];
                explode(y, x);
            }

            displayField(fieldmap);
            sleep(3); // sleep 0.3s
        }
    }

    // 爆発に巻き込まれたところを*で表示
    private void explode(int y, int x) {
        fieldmap[y][x].character = "*";

        int[][] surround = Control.surroundField(y, x);
        for(int i = 0; i < surround.length; i++) {
            int y2 = surround[i][0];
            int x2 = surround[i][1];

            // 周囲の地雷以外のマスの文字を*に変更
            // (爆発していない地雷マスはqueueに追加)
            Cell cell = fieldmap[y2][x2];
            if(cell instanceof Mine) {
                if(! (cell.character.equals("*") || queue.get(enqueued).contains(surround[i])))
                    queue.get(enqueued).add(surround[i]);
            } else
                cell.character = "*";
        }
    }
}
