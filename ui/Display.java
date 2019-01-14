package ui;

import models.Cell;
import control.*;

// 基本的な表示を行うクラス
public class Display extends Color {

    public void action() {
        displayField(Field.fieldmap);
        System.out.println(Information.notification);

        System.out.println("Select action.");
        System.out.print("> ");
    }

    public void selection() {
        displayField(Field.fieldmap);
        System.out.println("");
        System.out.println(Information.notification);

        System.out.println("Select unit.");
        System.out.print("> ");
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
