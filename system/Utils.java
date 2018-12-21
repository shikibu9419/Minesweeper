package system;

import java.util.*;

public class Utils {
    private static Random rand = new Random();
    public static int[] dx = {-1, -1, -1,  0, 0,  1, 1, 1};
    public static int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};

    // fieldの外にいたらtrue
    public static boolean outOfField(int y, int x) {
        return (x < 0 || x >= Field.MAX_X || y < 0 || y >= Field.MAX_Y);
    }

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
