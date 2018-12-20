package ui;

import java.util.*;
import java.io.*;

public class Display {
    private Scanner scan;

    public Display() {
        scan = new Scanner(System.in);
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

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++)
                System.out.print("|   ");
            System.out.print("|");
            System.out.println("");
            for(int j = 0; j < 20; j++)
                System.out.print("----");
            System.out.print("-");
            System.out.println("");
        }
        System.out.print("> ");
    }

    public void clearScreen() {
        try {
            new ProcessBuilder("/bin/bash","-c","clear").inheritIO().start().waitFor();
        } catch(IOException e) {
            System.out.println("IO Error");
        } catch(InterruptedException e) {
            System.out.println("I.R. Error");
        }
    }
}
