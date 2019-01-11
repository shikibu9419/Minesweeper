package control;

import models.*;

// ゲーム全体の情報を管理する
public class Information extends Control {

    // Field informations
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static final int MINE_COUNT = 50;

    // Unit informations
    protected static String[] allyCharacters = {"A", "B", "C"};
    public static int allies_count  = 3;
    public static int enemies_count = 3;
    public static Unit[] allies  = new Unit[allies_count];
    public static Unit[] enemies = new Unit[enemies_count];

    // Colors
    public static String RED    = "\u001b[00;31m";
    public static String GREEN  = "\u001b[00;32m";
    public static String YELLOW = "\u001b[00;33m";
    public static String BLUE   = "\u001b[00;34m";
    public static String PINK   = "\u001b[00;35m";
    public static String SKY    = "\u001b[00;36m";
    public static String END    = "\u001b[00m";

    public static String toRed(String str) {
        return RED + str + END;
    }

    public static String toYellow(String str) {
        return YELLOW + str + END;
    }

    public static String toBlue(String str) {
        return BLUE + str + END;
    }

    // Others
    public static String notification = "";
}
