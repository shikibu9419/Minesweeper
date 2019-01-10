package ui;

import models.Cell;
import control.Field;

// 基本的な表示を行うクラス
public class Display extends UI {

    public void show() {
        displayField(Field.fieldmap);
        showInformation();
    }

    private void showInformation() {
        System.out.println("");
    }
}
