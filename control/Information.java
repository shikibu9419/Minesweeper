package control;

import models.*;

// ゲーム全体の情報を管理する
public class Information extends Control {

    // Field informations
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static final int MINE_COUNT = 50;

    // Unit informations
    public static int allies_count  = 3;
    public static int enemies_count = 3;
    public static Unit[] allies  = new Unit[allies_count];
    public static Unit[] enemies = new Unit[enemies_count];

    // Colors
    public static String red = "\u001b[00;31m";
    public static String colorEnd = "\u001b[00m";

    // Others
    public static String notification = "";
}
