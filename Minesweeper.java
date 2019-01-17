import control.Field;
import ui.Main;

// ゲームをスタートさせるクラス
public class Minesweeper {
    public static void main(String[] args) {
        Field.initFieldmap();
        Main.start();
    }
}
