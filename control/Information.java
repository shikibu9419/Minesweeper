package control;

import models.*;

// ゲーム全体の情報を管理する
public class Information extends Control {

    // Field informations
//    protected static final int MAX_MINES = 100;
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static Cell[][] fieldmap = new Cell[MAX_Y][MAX_X];
    public static int minesCount;

    // Unit informations
//    private static final int MAX_ALLIES  = 3;
//    private static final int MAX_ENEMIES = 3;
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

    // Others
    public static void init(String n) {
        switch(n){
          case "1":
            difficultymode(3,1,100);
            break;
          case "2":
            difficultymode(3,5,100);
            break;
          case "3":
            difficultymode(3,50,100);
            break;
        }
        allies = new Unit[alliesCount];
        enemies = new Unit[enemiesCount];
        Field.initFieldmap();
        resetNotification();
    }

    //difficulty 1:easyW 2:normal 3:crazy
    public static void difficultymode(int a, int e, int m){
       alliesCount = a;
       enemiesCount = e;  
       minesCount = m; 
    }
} 
