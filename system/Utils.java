package system;

import java.util.*;

public class Utils {
    private static Random rand = new Random();

    // max未満のランダムな自然数を返す
    public static int randomInt(int max) {
        return rand.nextInt(max);
    }

    // ゲームの終了
    public static void exitGame() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
