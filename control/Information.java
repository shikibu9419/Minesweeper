package control;

import java.util.ArrayList;
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
    public static int alliesCount;
    public static int enemiesCount;

    // Notification
    public static String notification = "";

    public static void addNotification(String str) {
        notification += str + "\n";
    }

    public static void resetNotification() {
        notification = "";
    }

    // Initialization
    public static GameMode mode;
    public static Difficulty difficulty;

    public static void setGameMode(String order) {
        mode = GameMode.values()[Integer.parseInt(order) - 1];
    }

    public static void setDifficulty(String order) {
        difficulty = Difficulty.values()[Integer.parseInt(order) - 1];
    }

    public static void init() {
        alliesCount  = difficulty.allies;
        enemiesCount = mode.isPvP() ? alliesCount : difficulty.enemies;
        minesCount   = difficulty.mines;

        allies  = new Unit[alliesCount];
        enemies = new Unit[enemiesCount];

        Field.initFieldmap();
        resetNotification();
    }

    // Utilities
    // 周囲rangeマスのfield範囲内の座標一覧を[y][x]の配列にして返す
    public static int[][] surroundField(int y, int x, int range) {
        ArrayList<int[]> surround = new ArrayList<>();

        for(int i = y - range; i <= y + range; i++) {
            for(int j = x - range; j <= x + range; j++) {
                if(outOfField(i, j))
                    continue;
                int[] yx = {i, j};
                surround.add(yx);
            }
        }

        int[][] res = new int[surround.size()][2];
        for(int i = 0; i < surround.size(); i++)
            res[i] = surround.get(i);

        return res;
    }

    public static int[][] surroundField(int y, int x) {
        return surroundField(y, x, 1);
    }

    // fieldの外の座標ならtrueを返す
    public static boolean outOfField(int y, int x) {
        return (x < 0 || x >= Field.MAX_X || y < 0 || y >= Field.MAX_Y);
    }
}
