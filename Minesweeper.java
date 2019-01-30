import ui.InputReceiver;

// ゲームをスタートさせるクラス
public class Minesweeper {

    public static void main(String[] args) {
        while(true) {
            new InputReceiver().start();
        }
    }
}
