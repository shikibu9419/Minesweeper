package ui;

import control.Information;
import models.Unit;

// 基本的な表示を行うクラス
public class Main extends UI {

    private static Unit[] allies = Information.allies;
    private static Display display = new Display();
    private static InputReceiver receiver = new InputReceiver();

    public static void start() {
        while(true) {
            if(allDead()) {
                System.out.println("You lose...");
                exitGame();
            }

            int count = 0;
            while(count < allies.length) {
                Unit ally = allies[count];

                if(ally.isDead) {
                    count++;
                    continue;
                }

                display.show();

                System.out.print("Unit " + (count + 1) + " > ");

                if(receiver.receive(ally))
                    count++;

                // TODO: Enemy's action
            }
        }
    }

    private static boolean allDead() {
        boolean res = true;
        for(Unit ally:allies) {
            if(! ally.isDead)
                res = false;
        }
        return res;
    }
}
