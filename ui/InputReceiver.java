package ui;

import control.*;
import java.util.*;

// 入力受け付け
public class InputReceiver extends UI {

    private static Scanner scan = new Scanner(System.in).useDelimiter("\n");

    public boolean receive(int index) {
        UnitAction action = new UnitAction(Field.allies[index]);

        String[] order = scan.next().split(" |　");

        // 入力命令の解釈
        switch(order[0]) {
            // r/l/u/d
            case "r":
            case "l":
            case "u":
            case "d":
                return action.move(order[0]);
            // bomb (x) (y)
            case "bomb":
                int y = Integer.parseInt(order[2]) - 1;
                int x = Integer.parseInt(order[1]) - 1;
                return action.detonate(y, x);
            // exit
            case "exit":
                Control.exitGame();
            default:
                return false;
        }
    }
}
