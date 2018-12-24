package ui;

// 基本的な表示を行うクラス
public class Display extends UI {
    private InputReception input = new InputReception();

    public Display() {
        super();
    }

    public void start() {
        while(true) {
            displayField();
            input.receive();
        }
    }
}
