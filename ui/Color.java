package ui;

import models.*;

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
        if(cell.available)
            color = SKY;
        else if(cell instanceof Unit)
            color = ((Unit) cell).isAlly() ? BLUE : RED;
        else
            color = cell.detected ? GREEN : WHITE;

        return color + cell.character + END;
    }
}
