package ui;

import control.Field;

// 基本的な表示を行うクラス
public class Display extends UI {

    private InputReceiver receiver = new InputReceiver();

    public void start() {
        while(true) {
            displayField(Field.fieldmap);
            showConsole();
            receiver.receive();
        }
    }

    private void showConsole() {
        System.out.println("");
        System.out.print("> ");
    }
}
