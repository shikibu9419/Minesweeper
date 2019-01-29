package control;

import models.*;

// ゲーム全体の情報を管理する
public class Information extends Control {

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
}
