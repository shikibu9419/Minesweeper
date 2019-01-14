package ui;

import java.util.*;
import models.Unit;
import control.UnitAction;

// 入力受け付け
public class InputReceiver extends UI {

    private static Scanner scan = new Scanner(System.in).useDelimiter("\n");

    public int select() {
        String[] order = scan.next().split(" |　");
        switch (order[0]) {
            // units
            case "A":
            case "B":
            case "C":
                return (int)(order[0].getBytes()[0] - "A".getBytes()[0]);
            // finish
            case "f":
                return -1;
            default:
                return -2;
        }
    }

    public int actuate(Unit unit) {
        UnitAction action = new UnitAction(unit);
        String[] order = scan.next().split(" |　");

        // 入力命令の解釈
        // boolean を 1 or 0 にして返すの実装の敗北
        switch(order[0]) {
            // east/west/north/south
            case "e":
            case "w":
            case "n":
            case "s":
                return action.move(order[0]) ? 1 : 0;
            // bomb (x) (y)
            case "b":
                int y = Integer.parseInt(order[2]) - 1;
                int x = Integer.parseInt(order[1]) - 1;
                return action.detonate(y, x) ? 1 : 0;
            case "c":
                action.cancel();
                return -1;
            default:
                return 0;
        }
    }
}
