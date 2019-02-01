package options;

public enum Difficulty {
    EASY(1, 50),
    NORMAL(5, 100),
    CRAZY(50, 100);

    public final int allies = 3;
    public final int enemies;
    public final int mines;

    private Difficulty(int enemies, int mines) {
        this.enemies = enemies;
        this.mines   = mines;
    }
}
