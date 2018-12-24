package ui;

import control.Field;
import models.Cell;
import java.io.*;

// uiパッケージのメインクラス
public class UI {
    protected Cell[][] fieldmap;

    public UI() {
        fieldmap = Field.fieldmap;
    }

    protected Cell[][] latestField() {
        return Field.fieldmap;
    }

    protected void displayField() {
        clearScreen();

        // xの目盛表示
        for(int i = 0; i <= Field.MAX_X; i++)
            System.out.printf("%2d ", i);
        System.out.println("");

        for(int i = 0; i < Field.MAX_Y; i++) {
            // yの目盛表示
            System.out.printf("%2d ", i + 1);
            // fieldの表示
            for(int j = 0; j < Field.MAX_X; j++)
                System.out.printf("%2s ", fieldmap[i][j].getChar());
            System.out.println("");
        }
    }

    // CUI画面のクリア
    private void clearScreen() {
        try {
            new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
            return;
        } catch(IOException e) {
            System.out.println("IO Error occurred.");
        } catch(InterruptedException e) {
            System.out.println("I.R. Error occurred.");
        }
        System.exit(1);
    }
}
