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

            // ターン終了 (f) が指定されるまで
            while(true) {
                display.selection();

                int index = receiver.select();
                if(index == -1)
                    break;
                else if(index < -1)
                    continue;

                Unit ally = allies[index];
                ally.updateAvailable(true);

                // ユニットの行動が完了するまで
                while(true) {
                    display.action(ally);

                    int result = receiver.actuate(ally);
                    if(result == 0)
                        continue;
                    if(result == 1)
                        ally.acted = true;

                    break;
                }

                ally.updateAvailable(false);
            }

            resetAllies();
            Information.resetNotification();
            opponent.start();
        }
    }

    private static void resetAllies() {
        for(Unit ally:allies)
            ally.acted = false;
    }
}
