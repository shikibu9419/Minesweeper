package control;

import models.*;

// ゲーム全体の情報を管理する
public class Information extends Control {

    // Field informations
    protected static final int MAX_Y = 20;
    protected static final int MAX_X = 20;
    protected static final int MINE_COUNT = 100;
    public static Cell[][] fieldmap = new Cell[MAX_Y][MAX_X];

    // Unit informations
    private static final int MAX_ALLIES  = 3;
    private static final int MAX_ENEMIES = 3;
    public static final int AVAILABLE_RANGE = 3;
    public static Unit[] allies  = new Unit[MAX_ALLIES];
    public static Unit[] enemies = new Unit[MAX_ENEMIES];
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

    // Others
    public static void init() {
        alliesCount  = MAX_ALLIES;
        enemiesCount = MAX_ENEMIES;
        Field.initFieldmap();
        resetNotification();
    }
}
