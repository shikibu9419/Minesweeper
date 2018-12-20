package ui;

import models.*;
import system.*;
import java.util.*;

// 入力受け付け
public class InputReception {
    private Scanner scan;

    public InputReception() {
        // 改行区切りでのみ入力を受け付ける
        scan = new Scanner(System.in).useDelimiter("\n");
    }

    public void receive(Unit unit) {
        UnitAction action = new UnitAction(unit);

        System.out.println("");
        System.out.print("> ");
        // 入力は空白区切りの配列で受け取る
        String[] order = scan.next().split(" |　");

        switch(order[0]) {
            case "move":
                // move r/l/u/d みたいな
                action.move(order[1].charAt(0));
                break;
            case "exit":
                exitGame();
                break;
            default:
                System.out.println(order);
        }
    }

    // ゲームの終了
    private void exitGame() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
