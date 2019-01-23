package ui;

import models.Cell;
import control.Information;

// 基本的な表示を行うクラス
public class Display extends Color {

    public void battleProgress(){
      String progress = String.format("Allies: %d  Enemies: %d  Mines:  %d",
                                      Information.alliesCount,
                                      Information.enemiesCount,
                                      Information.minesCount);
      System.out.printf("%62s\n", progress);
      System.out.println();
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
        displayField(Information.fieldmap);
        battleProgress();
        System.out.println(Information.notification);
    }

    // fieldを表示
    protected void displayField(Cell[][] fieldmap) {
        final int MAX_Y = fieldmap.length;
        final int MAX_X = fieldmap[0].length;

        clearScreen();

        // xの目盛表示
        for(int i = 0; i <= MAX_X; i++)
            System.out.printf("%2d ", i);
        System.out.println("");

        // yの目盛表示
        for(int i = 0; i < MAX_Y; i++) {
            System.out.printf("%2d ", i + 1);
            // fieldの表示
            for(int j = 0; j < MAX_X; j++)
                System.out.printf(" %s ", decorate(fieldmap[i][j]));
            System.out.println("");
        }

    }

    // shellコンソール表示のクリア
    private void clearScreen() {
        try {
            new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void showMessage(String... msgs) {
        for(String msg:msgs)
            System.out.println(msg);
        System.out.println("");
    }
}
