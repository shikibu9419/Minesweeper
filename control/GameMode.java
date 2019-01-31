package control;

public enum GameMode {
    PvP, // Player   vs Player
    PvC, // Player   vs Computer
    CvC; // Computer vs Computer

    public boolean isPvP() {
        return this == PvP;
    }

    public boolean isPvC() {
        return this == PvC;
    }

    public boolean isCvC() {
        return this == CvC;
    }
}
