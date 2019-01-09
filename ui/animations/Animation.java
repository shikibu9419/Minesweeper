package ui.animations;

import ui.UI;
import models.Cell;
import control.Field;

// animationパッケージのメインクラス
public class Animation extends UI {

    protected Cell[][] fieldmap;

    // 本体に影響がないようにfieldmapのディープコピーを渡す
    public Animation() {
        setFieldmap();
    }

    private void setFieldmap() {
        final int MAX_Y = Field.fieldmap.length;
        final int MAX_X = Field.fieldmap[0].length;

        fieldmap = new Cell[MAX_Y][MAX_X];
        for(int i = 0; i < MAX_Y; i++)
            for(int j = 0; j < MAX_X; j++)
                fieldmap[i][j] = Field.fieldmap[i][j].clone();
    }

    // sec * 0.1 秒待機
    protected void sleep(int sec) {
        try {
            Thread.sleep(sec * 100);
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
