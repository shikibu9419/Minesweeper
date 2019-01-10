package ui.animations;

import ui.UI;
import models.Cell;
import control.Field;

// animationパッケージのメインクラス
public class Animation extends UI {

    protected Cell[][] fieldmap;

    public Animation() {
        fieldmap = Field.getClone();
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
