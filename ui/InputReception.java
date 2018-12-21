package ui;

import models.*;
import system.*;
import java.util.*;

// 入力受け付け
public class InputReception {

    public void receive() {
        // 入力を改行区切りで受け付ける
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        // ユニットの操作設定用インスタンス
        UnitAction action = new UnitAction(Field.unit);

        System.out.println("");
        System.out.print("> ");
        // 空白で区切った配列として受け取る
        String[] order = scan.next().split(" |　");

        switch(order[0]) {
            // 移動
            case "right":
            case "left":
            case "up":
            case "down":
                action.move(order[0]);
                break;
            // 終了
            case "exit":
                Utils.exitGame();
                break;
            default:
                System.out.println(order);
        }
    }
}
