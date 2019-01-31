package ui;

import models.*;
import control.Information;
import configs.Color;

// 基本的な表示を行うクラス
public class Display implements Color {

    public void modeSelection() {
        clearScreen();
        showMessage("Select game mode:",
                    "   1  player   vs player",
                    "   2  player   vs computer",
                    "   3  computer vs computer");

        System.out.print("> ");
    }

    public void diffSelection() {
        clearScreen();
        showMessage("Select difficulty number:",
                    "   1  easy",
                    "   2  normal",
                    "   3  CRAZY");

        System.out.print("> ");
    }

    public void selection() {
        showInformation();
        showMessage("Enter one of following commands:",
                    "   A/B/C  Select unit A / B / C",
                    "   f      Finish your turn",
                    "   q      Quit this game");
        System.out.print("> ");
    }

    public void action(Cell cell) {
        showInformation();
        showMessage("Enter one of following commands:",
                    "   w/s/a/d     Move to up / down / left / right",
                    "   b (x) (y)   If the mine is on (x, y), blow it up (within the range of light blue)",
                    "   c           Cancel selection");
        System.out.println("Unit " + decorate(cell));
        System.out.print("> ");
    }


    private void showInformation() {
        String progress = String.format("Allies: %d  Enemies: %d  Mines: %d",
                                        Information.alliesCount, Information.enemiesCount, Information.minesCount);

        displayField(Information.fieldmap);
        System.out.printf("%62s", progress);
        showMessage("", "", Information.notification);
    }

    // fieldを表示
    protected void displayField(Cell[][] fieldmap) {
        final int MAX_Y = fieldmap.length;
        final int MAX_X = fieldmap[0].length;

        clearScreen();

        // xの目盛表示
        for(int i = 0; i <= MAX_X; i++)
            System.out.printf("%2d ", i);
        System.out.println();

        // yの目盛表示
        for(int i = 0; i < MAX_Y; i++) {
            System.out.printf("%2d ", i + 1);
            // fieldmapの表示
            for(int j = 0; j < MAX_X; j++)
                System.out.printf(" %s ", decorate(fieldmap[i][j]));
            System.out.println();
        }
    }

    private String decorate(Cell cell) {
        String color;

        if(cell.character.equals("*"))
            color = YELLOW;                         // exploded
        else if(cell.available)
            color = SKY;                            // available
        else if(cell instanceof Unit) {
            Unit unit = (Unit)cell;
            if(unit.acted)
                color = GREEN;                      // acted
            else
                color = unit.isAlly() ? BLUE : RED; // unacted
        } else
            color = cell.detected ? GREEN : WHITE;  // mine, flatland

        return color + cell.character + END;
    }

    private void showMessage(String... msgs) {
        for(String msg:msgs)
            System.out.println(msg);
        System.out.println();
    }

    private void clearScreen() {
        try {
            new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
