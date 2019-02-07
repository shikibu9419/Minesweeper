package ui.animations;

import java.util.*;
import control.Field;
import models.*;

// 爆発アニメーション
public class ExplodeAnimation extends Animation {

    private List<Queue<Cell>> queueList = new ArrayList<>();
    private int enqueued = 0, dequeued = 1;

    public ExplodeAnimation() {
        super();
        queueList.add(new ArrayDeque<Cell>());
        queueList.add(new ArrayDeque<Cell>());
    }

    public void start(int y, int x) {
        queueList.get(enqueued).add(fieldmap[y][x]);

        // 幅優先爆発
        while(! queueList.get(enqueued).isEmpty()) {
            enqueued = (enqueued + 1) % 2;
            dequeued = (dequeued + 1) % 2;

            while(! queueList.get(dequeued).isEmpty())
                explode(queueList.get(dequeued).remove());

            displayField(fieldmap);
            sleep(2); // sleep 0.2s
        }
    }

    // 爆発に巻き込まれたところを*で表示 (未爆発の地雷は誘爆する)
    private void explode(Cell mine) {
        mine.character = "*";

        for(Cell cell:Field.surrounds(mine, fieldmap)) {
            if(cell instanceof Mine) {
                if(! (cell.character.equals("*") || queueList.get(enqueued).contains(cell)))
                    queueList.get(enqueued).add(cell);
            } else
                cell.character = "*";
        }
    }
}
