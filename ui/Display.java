package ui;

import system.*;
import java.util.*;
import java.io.*;

// 表示と入力インターフェースを担当するクラス
public class Display {
    private Field field;
    private Scanner scan;
    private InputReception input;

    public Display() {
        field = new Field();
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
        for(int i = 0; i <= field.MAX_X; i++)
            System.out.printf("%2d ", i);
        System.out.println("");

        for(int i = 0; i < field.MAX_Y; i++) {
            // yの座標表示
            System.out.printf("%2d ", i + 1);
            // Field表示
            for(int j = 0; j < field.MAX_X; j++)
                System.out.print(" " + field.fieldmap[i][j].getChar() + " ");
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

////  マス目を頑張るパターン
//     public void display() {
//         clearScreen();
//
//         for(int i = 0; i < MAX_Y; i++) {
//             for(int j = 0; j < 20; j++)
//                 System.out.print("|   ");
//             System.out.print("|");
//             System.out.println("");
//             for(int j = 0; j < 20; j++)
//                 System.out.print("----");
//             System.out.print("-");
//             System.out.println("");
//         }
//
//         System.out.print("> ");
//     }
}
