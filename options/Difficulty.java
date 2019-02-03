package options;

public enum Difficulty {
    EASY(1, 50),
    NORMAL(5, 100),
    CRAZY(50, 100);

    public final int ALLIES = 3;
    public final int ENEMIES;
    public final int MINES;

    private Difficulty(int enemies, int mines) {
        ENEMIES = enemies;
        MINES   = mines;
    }
}
