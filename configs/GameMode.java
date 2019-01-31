package configs;

public enum GameMode {
    P_V_P,  // Player vs Player
    P_V_C,  // Player vs Computer
    C_V_C;  // Computer vs Computer

    public boolean isPvP() {
        return this == P_V_P;
    }

    public boolean isPvC() {
        return this == P_V_C;
    }

    public boolean isCvC() {
        return this == C_V_C;
    }
}
