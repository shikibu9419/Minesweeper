package ui;

import control.Field;

// 基本的な表示を行うクラス
public class Display extends UI {

    public void start() {
        while(true) {
            int index = 0;
            while(index < Field.allies.length) {
                displayField(Field.fieldmap);
                showInformation();

                System.out.print("Unit " + (index + 1) + " > ");

                if(new InputReceiver().receive(index))
                    index++;

                // TODO: Enemy's action
            }
        }
    }

    private void showInformation() {
        System.out.println("");
    }
}
