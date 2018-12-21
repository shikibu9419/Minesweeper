package ui;

import system.Field;
import java.io.*;

// 表示と入力インターフェースを担当するクラス
public class Display {
    private InputReception input;

    public Display() {
        Field.initFieldmap();
        input = new InputReception();
    }

    public void start() {
        while(true) {
            displayField();
            input.receive();
        }
    }

    private void displayField() {
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
                System.out.print(" " + Field.fieldmap[i][j].getChar() + " ");
            System.out.println("");
        }
    }

    // CUI画面のクリア
    private void clearScreen() {
        try {
            new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
        } catch(IOException e) {
            System.out.println("IO Error occurred.");
            System.exit(1);
        } catch(InterruptedException e) {
            System.out.println("I.R. Error occurred.");
            System.exit(1);
        }
    }
}
