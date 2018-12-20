package ui;

import system.*;
import java.util.*;
import java.io.*;

// 表示と入力インターフェースを担当するクラス
public class Display {
    private final int MAX_X;
    private final int MAX_Y;
    private final Cell[][] fieldmap;
    private Field field;
    private Scanner scan;
    private InputReception input;

    public Display() {
        field = new Field();
        scan  = new Scanner(System.in);
        input = new InputReception();

        MAX_X = field.MAX_X;
        MAX_Y = field.MAX_Y;
        fieldmap = field.fieldmap;
    }

    public void start() {
        while(true) {
            display();
            input.receive(scan.next());
        }
    }

    private void display() {
        clearScreen();

        for(int i = 0; i <= MAX_X; i++)
            System.out.printf("%2d ", i);
        System.out.println("");

        for(int i = 0; i < MAX_Y; i++) {
            System.out.printf("%2d ", i + 1);
            for(int j = 0; j < MAX_X; j++)
                System.out.print(" " + field.fieldmap[i][j].getChar() + " ");
            System.out.println("");
        }

        System.out.println("");
        System.out.print("> ");
    }

    private void clearScreen() {
        try {
            new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
        } catch(IOException e) {
            System.out.println("IO Error occurred.");
        } catch(InterruptedException e) {
            System.out.println("I.R. Error occurred.");
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
