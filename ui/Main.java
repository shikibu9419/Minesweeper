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
        while(true) {
            if(Information.allDead("ally")) {
                System.out.println("You lose...");
                exitGame();
            } else if(Information.allDead("enemy")) {
                System.out.println("You win!");
                exitGame();
            }

            for(int count = 0; count < allies.length; count++) {
                Unit ally = allies[count];

                if(ally.isDead)
                    continue;

                display.show();

                System.out.print(ally.character + " > ");

                if(! receiver.receive(ally))
                    count--;
            }
            opponent.start();
        }
    }
}
