package control;

import models.*;

// ゲーム全体の情報を管理する
public interface Information {

    // Field informations
    public static final int MAX_Y = 20;
    public static final int MAX_X = 20;
    public static final int MINE_COUNT = 50;

    // Unit informations
    public static final int ALLIES_COUNT  = 3;
    public static final int ENEMIES_COUNT = 3;
    public static Unit[] allies  = new Unit[ALLIES_COUNT];
    public static Unit[] enemies = new Unit[ENEMIES_COUNT];
}
