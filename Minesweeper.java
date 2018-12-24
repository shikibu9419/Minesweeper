import control.Field;
import ui.Display;

public class Minesweeper {
    public static void main(String[] args) {
        Field.initFieldmap();
        new Display().start();
    }
}
