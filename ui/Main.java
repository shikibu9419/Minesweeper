package ui;

import algorithm.Opponent;
import control.Information;
import models.Unit;

// 基本的な表示を行うクラス
public class Main extends UI {

    private static Unit[] allies = Information.allies;
    private static Display display = new Display();
    private static InputReceiver receiver = new InputReceiver();
    private static Opponent opponent = new Opponent();

    public static void start() {
        // ゲーム開始
        while(true) {
            if(Information.allDead("ally")) {
                System.out.println("You lose...");
                exitGame();
            } else if(Information.allDead("enemy")) {
                System.out.println("You win!");
                exitGame();
            }

            // finish が指定されるまで
            while(true) {
                display.selection();

                int index = receiver.select();
                if(index == -1)
                    break;
                else if(index < -1)
                    continue;

                Unit ally = allies[index];
                ally.color = "sky";

                // ユニットの行動が完了するまで
                while(true) {
                    display.action();
                    if(receiver.actuate(ally)) {
                        ally.color = "green";
                        break;
                    }
                }
            }

            resetAllies();
            Information.resetNotification();
            opponent.start();
        }
    }

    private static void resetAllies() {
        for(Unit ally:allies)
            ally.color = "blue";
    }
}
