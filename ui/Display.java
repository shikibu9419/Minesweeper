import java.util.*;
import java.io.*;

public class Display{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);

    String input;

    for(int i = 0; i < 20; i++){
      for(int j = 0; j < 20; j++){
        System.out.print("_|");
      }
      System.out.println("");
    }
    
    System.out.print("> ");
    input = scan.next();
    try{
      clearScreen();
      System.out.println(input);
    }catch(IOException e){
      System.out.println("IO Error");
    }catch(InterruptedException e){
      System.out.println("I.R. Error");
    }
  }
  public static void clearScreen() throws IOException, InterruptedException{
    new ProcessBuilder("/bin/bash","-c","clear").inheritIO().start().waitFor();
  }
}

