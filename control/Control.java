package control;

import java.util.*;

// controlパッケージのメインクラス
public class Control {

    private static Random rand = new Random();
    private static int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};
    private static int[] dx = {-1, -1, -1,  0, 0,  1, 1, 1};

    // 周囲のfield範囲内の座標一覧を[y][x]の配列にして返す
    public static int[][] surroundField(int y, int x) {
        ArrayList<int[]> surround = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            int y2 = y + dy[i];
            int x2 = x + dx[i];
            if (outOfField(y2, x2))
                continue;

            int[] yx = {y2, x2};
            surround.add(yx);
        }

        // ArrayList -> Array の変換でおそらく最善の方法
        int[][] res = new int[surround.size()][2];
        for(int i = 0; i < surround.size(); i++)
            res[i] = surround.get(i);

        return res;
    }

    // fieldの外の座標ならtrueを返す
    public static boolean outOfField(int y, int x) {
        return (x < 0 || x >= Field.MAX_X || y < 0 || y >= Field.MAX_Y);
    }

    // max未満のランダムな自然数を返す
    public static int randomInt(int max) {
        return rand.nextInt(max);
    }
}
