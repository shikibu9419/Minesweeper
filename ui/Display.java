package ui;

import control.Field;

// 基本的な表示を行うクラス
public class Display extends UI {

    private InputReception input = new InputReception();

    public void start() {
        while(true) {
            displayField(Field.fieldmap);
            input.receive();
        }
    }
}
