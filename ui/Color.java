package ui;

import models.*;

public class Color extends UI {

    private final String RED    = "\u001b[01;31m";  // for enemy
    private final String GREEN  = "\u001b[01;32m";  // acted ally & detected cell
    private final String YELLOW = "\u001b[01;33m";  // exploded cell
    private final String BLUE   = "\u001b[01;34m";  // standby ally
    private final String PINK   = "\u001b[01;35m";
    private final String SKY    = "\u001b[01;36m";  // selected ally & available cell
    private final String WHITE  = "\u001b[01;37m";  // normal cell
    private final String END    = "\u001b[00m";

    protected String decorate(Cell cell) {
        String color = WHITE;

        if(cell.character.equals("*"))
            color = YELLOW;  // exploded
        else if(cell.available)
            color = SKY;     // available
        else if(cell instanceof Unit) {
            Unit unit = (Unit)cell;
            if(unit.isAlly())
                color = unit.acted ? GREEN : BLUE;  // ally
            else
                color = RED;  // enemy
        } else
            color = cell.detected ? GREEN : WHITE;  // mine, flatland

        return color + cell.character + END;
    }
}
