package system;

import java.util.*;

public class Utils {
    private static Random rand = new Random();
    private static int[] dx = {-1, -1, -1,  0, 0,  1, 1, 1};
    private static int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};

    // 周りの存在するcellの座標を配列にして返す
    public static int[][] surroundingField(int y, int x) {
        ArrayList<int[]> surround = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            int x2 = x + dx[i];
            int y2 = y + dy[i];
            if (outOfField(y2, x2))
                continue;

            int[] yx = {y2, x2};
            surround.add(yx);
        }

        int[][] res = new int[surround.size()][2];
        for(int i = 0; i < surround.size(); i++) {
            res[i] = surround.get(i);
        }

        return res;
    }

    // fieldの外の座標ならtrue
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
