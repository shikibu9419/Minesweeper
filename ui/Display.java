package ui;

import models.Cell;
import control.Information;

// 基本的な表示を行うクラス
public class Display extends Color {

    public void selection() {
        showInformation();

        System.out.println("Enter one of following commands:");
        System.out.println("  A/B/C  Select unit A / B / C");
        System.out.println("  f      Finish your turn");
        System.out.println("");
        System.out.print("> ");
    }

    public void action(Cell cell) {
        showInformation();

        System.out.println("Enter one of following commands:");
        System.out.println("  e/w/n/s     Move to east / west / north / south");
        System.out.println("  b (x) (y)   If the mine is on (x, y) blow it up");
        System.out.println("  c           Cancel selection");
        System.out.println("");
        System.out.print("Unit " + decorate(cell) + " > ");
    }

    private void showInformation() {
        displayField(Information.fieldmap);

        System.out.println("");
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
}
