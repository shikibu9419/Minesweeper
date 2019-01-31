package configs;

public enum UnitType {
    ALLY, ENEMY;

    public boolean isAlly() {
        return this == ALLY;
    }
}
