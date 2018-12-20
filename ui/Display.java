package ui;

import system.*;
import java.util.*;
import java.io.*;

// 表示と入力インターフェースを担当するクラス
public class Display {
    private Scanner scan;
    private InputReception input;

    public Display() {
        // fieldmapの初期化
        Field.initFieldmap();
        scan  = new Scanner(System.in);
        input = new InputReception();
    }

    public void start() {
        while(true) {
            displayField();

            // 入力受け付け
            System.out.println("");
            System.out.print("> ");
            input.receive(scan.next());
        }
    }

    private void displayField() {
        clearScreen();

        // xの座標表示
        for(int i = 0; i <= Field.MAX_X; i++)
            System.out.printf("%2d ", i);
        System.out.println("");

        for(int i = 0; i < Field.MAX_Y; i++) {
            // yの座標表示
            System.out.printf("%2d ", i + 1);
            // Field表示
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
