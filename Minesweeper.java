import control.Information;
import ui.InputReceiver;

// ゲームをスタートさせるクラス
public class Minesweeper {
    public static void main(String[] args) {
        while(true) {
            Information.init();
            new InputReceiver().start();
        }
    }
}
