package control;

import java.util.*;
import models.*;

// ゲーム全体の情報を管理する
public class Information {

    // Field informations
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static Cell[][] fieldmap = new Cell[MAX_Y][MAX_X];
    public static int minesCount;

    // Unit informations
    public static final int AVAILABLE_RANGE = 3;
    public static Unit[] allies;
    public static Unit[] enemies;
    public static int alliesCount = 3;
    public static int enemiesCount;

    // Notification
    public static String notification = "";

    public static void addNotification(String str) {
        notification += str + "\n";
    }

    public static void resetNotification() {
        notification = "";
    }

    // Others
    public static void init(String n) {
        switch(n){
          case "1":
            difficultymode(1,100);
            break;
          case "2":
            difficultymode(5,100);
            break;
          case "3":
            difficultymode(50,100);
            break;
        }
        allies = new Unit[alliesCount];
        enemies = new Unit[enemiesCount];
        Field.initFieldmap();
        resetNotification();
    }

    public static void difficultymode(int e, int m){
       enemiesCount = e;
       minesCount = m;
    }

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
