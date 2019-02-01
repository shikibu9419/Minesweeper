package control;

import models.*;
import options.*;

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
        enemiesCount = mode.isPvP() ? alliesCount : difficulty.enemies;  // PvPのみユニットの数は同じ
        minesCount   = difficulty.mines;

        allies  = new Unit[alliesCount];
        enemies = new Unit[enemiesCount];

        Field.initFieldmap();
        resetNotification();
    }
}
