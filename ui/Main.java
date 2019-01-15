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
            // 暫定実装
            if(Information.isAllDead("ally")) {
                System.out.println("You lose...");
                exitGame();
            } else if(Information.isAllDead("enemy")) {
                System.out.println("You win!");
                exitGame();
            }

            // ターン終了 (f) が指定されるまで
            while(true) {
                display.selection();

                int selected = receiver.select();
                // error
                if(selected < -1)
                    continue;
                // finish
                if(selected == -1)
                    break;

                Unit ally = allies[selected];
                ally.updateAvailable(true);

                // ユニットの行動が完了するまで
                while(! ally.acted) {
                    display.action(ally);

                    int result = receiver.actuate(ally);
                    // error
                    if(result < -1)
                        continue;
                    // cancel
                    if(result == -1)
                        break;
                    // success
                    if(result == 1)
                        ally.acted = true;
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
