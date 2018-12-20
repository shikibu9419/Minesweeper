package ui;

import java.util.*;
import java.io.*;

public class Display {
    private int NUM_X;
    private int NUM_Y;
    private Scanner scan = new Scanner(System.in);
    private Field field = new Field();

    public Display() {
        NUM_X = field.NUM_X;
        NUM_Y = field.NUM_Y;
    }

    public void start() {
        while(true) {
            display();
            String input = scan.next();
            System.out.println(input);
        }
    }

    private void display() {
        clearScreen();

        for(int i = 0; i <= NUM_X; i++)
            System.out.printf("%2d ", i);
        System.out.println("");

        for(int i = 0; i < NUM_Y; i++) {
            System.out.printf("%2d ", i + 1);
            for(int j = 0; j < NUM_X; j++)
                System.out.print(" " + field.fieldmap[i][j].getChar() + " ");
            System.out.println("");
        }

        System.out.println("");
        System.out.print("> ");
    }

    private void clearScreen() {
        try {
            new ProcessBuilder("/bin/bash","-c","clear").inheritIO().start().waitFor();
        } catch(IOException e) {
            System.out.println("IO Error occurred.");
        } catch(InterruptedException e) {
            System.out.println("I.R. Error occurred.");
        }
    }

//     public void display() {
//         clearScreen();
//
//         for(int i = 0; i < NUM_Y; i++) {
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
