package ui;

import models.*;
import control.*;
import java.util.*;

// 入力受け付け
public class InputReception extends UI {

    private Unit unit = Field.unit;
    private UnitAction action = new UnitAction(unit);

    public void receive() {
        //   ユニットがゴールに到達したらクリア (暫定実装)
        if(Field.fieldmap[Field.MAX_Y - 1][Field.MAX_X - 1] instanceof Unit)
            Control.goal();

        Scanner scan = new Scanner(System.in).useDelimiter("\n");

        System.out.println("");
        System.out.print("> ");

        String[] order = scan.next().split(" |　");

        // 入力命令の解釈
        switch(order[0]) {
            // 移動
            case "r":
            case "l":
            case "u":
            case "d":
                action.move(order[0]);
                break;
            // 終了
            case "exit":
                Control.exitGame();
                break;
            default:
                System.out.println(order);
        }
    }
}
