package ui.animations;

import ui.UI;
import models.Cell;
import control.Field;

// animationパッケージのメインクラス
public class Animation extends UI {

    protected Cell[][] fieldmap;

    // 本体に影響がないようにコピーを渡す
    public Animation() {
        fieldmap = Field.fieldmap.clone();
    }

    // sec * 0.1 秒待機
    protected void sleep(int sec) {
        try {
            Thread.sleep(sec * 100);
            return;
        } catch(InterruptedException e) {
            System.out.println("InterruptedException Occurred.\n" + e);
        }
        System.exit(1);
    }
}
