package options;

public enum UnitType {
    ALLY, ENEMY;

    public boolean isAlly() {
        return this == ALLY;
    }
}
