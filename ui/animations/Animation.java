package ui.animations;

import models.Cell;
import control.Field;
import ui.UI;

// animationパッケージのメインクラス
public class Animation extends UI {

    protected Cell[][] fieldmap;

    // 本体に影響がないようにコピーを渡す
    public Animation() {
        fieldmap = Field.fieldmap.clone();
    }

    // Animationではコピーを表示する
    protected void displayField(Cell[][] fieldmap) {
        display(fieldmap);
    }

    // 指定された秒数だけ待機
    protected void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
            return;
        } catch(InterruptedException e) {
            System.out.println("InterruptedException Occurred.");
        }
        System.exit(1);
    }
}
