package ui;

import models.Cell;

public class Color extends UI {

    private final String RED    = "\u001b[01;31m";
    private final String GREEN  = "\u001b[01;32m";
    private final String YELLOW = "\u001b[01;33m";
    private final String BLUE   = "\u001b[01;34m";
    private final String PINK   = "\u001b[01;35m";
    private final String SKY    = "\u001b[01;36m";  // light blue
    private final String WHITE  = "\u001b[01;37m";
    private final String END    = "\u001b[00m";

    protected String decorate(Cell cell) {
        String color = WHITE;
        switch(cell.color) {
            case "red":
                color = RED;
                break;
            case "green":
                color = GREEN;
                break;
            case "yellow":
                color = YELLOW;
                break;
            case "blue":
                color = BLUE;
                break;
            case "pink":
                color = PINK;
                break;
            case "sky":
                color = SKY;
                break;
        }
        return color + cell.character + END;
    }
}
