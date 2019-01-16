import control.Field;
import ui.InputReceiver;

// ゲームをスタートさせるクラス
public class Minesweeper {
    public static void main(String[] args) {
        while(true) {
            Field.initFieldmap();
            new InputReceiver().start();
        }
    }
}
