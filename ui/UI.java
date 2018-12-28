package ui;

import models.Cell;
import java.io.*;

// uiパッケージのメインクラス
public class UI {

    // fieldを表示
    protected void displayField(Cell[][] fieldmap) {
        final int MAX_Y = fieldmap.length;
        final int MAX_X = fieldmap[0].length;

        clearScreen();

        for(int i = 0; i <= MAX_X; i++)
            // xの目盛表示
            System.out.printf("%2d ", i);
        System.out.println("");

        for(int i = 0; i < MAX_Y; i++) {
            // yの目盛表示
            System.out.printf("%2d ", i + 1);
            // fieldの表示
            for(int j = 0; j < MAX_X; j++)
                System.out.printf("%2s ", fieldmap[i][j].getChar());
            System.out.println("");
        }
    }

    // shellコンソール表示のクリア
    private void clearScreen() {
        try {
            new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
            return;
        } catch(IOException e) {
            System.out.println("IO Error occurred.\n" + e);
        } catch(InterruptedException e) {
            System.out.println("I.R. Error occurred.\n" + e);
        }
        System.exit(1);
    }
}
