package control;

import models.*;

// ゲーム全体の情報を管理する
public class Information extends Control {

    // Field informations
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static final int MINE_COUNT = 100;
    public static Cell[][] fieldmap = new Cell[MAX_Y][MAX_X];

    // Unit informations
    public static final int availableRange = 3;
    public static int alliesCount  = 3;
    public static int enemiesCount = 3;
    public static Unit[] allies  = new Unit[alliesCount];
    public static Unit[] enemies = new Unit[enemiesCount];

    public static boolean isAllDead(String type) {
        Unit[] units = type.equals("ally") ? allies : enemies;
        boolean res = true;
        for(Unit unit:units)
            if(! unit.dead)
                res = false;
        return res;
    }

    // Others
    public static String notification = "";

    public static void addNotification(String str) {
        notification += str + "\n";
    }

    public static void resetNotification() {
        notification = "";
    }
}
