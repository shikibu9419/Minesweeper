package ui;

import java.util.*;
import models.Unit;
import control.*;
import algorithm.Opponent;

// 入力受け付け
public class InputReceiver extends UI {

    private Unit[]   allies   = Information.allies;
    private Scanner  scan     = new Scanner(System.in).useDelimiter("\n");
    private Display  display  = new Display();
    private Opponent opponent = new Opponent();

    public void start() {
        // ゲーム開始
        while(true) {
            judge();
            select();

            for(Unit ally:allies)
                ally.acted = false;
            Information.resetNotification();
            opponent.start();
        }
    }

    private boolean select() {
        Unit ally;
        while(true) {
            judge();
            display.selection();

            String[] order = scan.next().split(" ");
            switch (order[0]) {
                // units
                case "a":
                case "A":
                    ally = allies[0];
                    break;
                case "b":
                case "B":
                    ally = allies[1];
                    break;
                case "c":
                case "C":
                    ally = allies[2];
                    break;
                case "d":
                case "D":
                    ally = allies[3];
                    break;
                case "e":
                case "E":
                    ally = allies[4];
                    break;
                // finish
                case "f":
                    return true;
                default:
                    continue;
            }

            if(isDisabled(ally))
                continue;

            ally.updateAvailable(true);
            while(! actuate(ally));
            ally.updateAvailable(false);
        }
    }

    private boolean actuate(Unit ally) {
        boolean result = false;
        UnitAction action = new UnitAction(ally);

        while(! result) {
            display.action(ally);

            String[] order = scan.next().split(" ");
            switch(order[0]) {
                // east/west/north/south
                case "a":
                case "d":
                case "w":
                case "s":
                    result = action.move(order[0]);
                    break;
                // bomb (x) (y)
                case "b":
                    if(order.length < 3)
                        return false;
                    int y = Integer.parseInt(order[2]) - 1;
                    int x = Integer.parseInt(order[1]) - 1;
                    result = action.detonate(y, x);
                    break;
                case "c":
                    action.cancel();
                    return true;
                default:
                    return false;
            }
        }

        ally.acted = true;
        return true;
    }

    private void judge() {
        if(Information.isAllDead("ally")) {
            System.out.println("You lose...");
            exitGame();
        } else if(Information.isAllDead("enemy")) {
            System.out.println("You win!");
            exitGame();
        }
    }

    private void exitGame() {
        System.out.println("Continue? (y/n)");
        System.out.print("> ");

        String[] order = scan.next().split(" ");
        if(order[0].equals("n")) {
            System.out.println("See you next time!");
            System.exit(0);
        }
    }

    private boolean isDisabled(Unit ally) {
        if(ally.dead) {
            Information.addNotification(String.format("Unit %s is already dead.", ally.character));
            return true;
        }
        if(ally.acted) {
            Information.addNotification(String.format("Unit %s already acted.", ally.character));
            return true;
        }
        return false;
    }
}
