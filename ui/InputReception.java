package ui;

import models.*;
import control.*;
import java.util.*;

// 入力受け付け
public class InputReception extends UI {
    private Unit unit = Field.unit;

    public void receive() {
        //   ユニットがゴールに到達したらクリア (暫定で実装)
        if(Field.fieldmap[Field.MAX_Y - 1][Field.MAX_X - 1] instanceof Unit)
            Control.goal();

        // 入力を改行区切りで受け付ける
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        // ユニットの操作設定用
        UnitAction action = new UnitAction(unit);

        System.out.println("");
        System.out.print("> ");

        // 空白で区切った配列として受け取る
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
