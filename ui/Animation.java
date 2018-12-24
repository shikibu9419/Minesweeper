package ui;

import models.Cell;
import control.Field;

// アニメーションのメインクラス
public class Animation extends UI {
    protected Cell[][] fieldmap;

    // 本体に影響がないようにコピーを渡す
    public Animation() {
        fieldmap = Field.fieldmap.clone();
    }

    // 本体のfieldmapを返す
    protected Cell[][] currentFieldmap() {
        return Field.fieldmap;
    }

    // 指定された秒数だけ待機
    protected void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch(InterruptedException e) {
            System.out.println("InterruptedException Occurred.");
        }
    }
}
