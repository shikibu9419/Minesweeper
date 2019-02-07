package ui;

import java.util.Scanner;
import algorithm.*;
import control.*;
import models.*;
import options.UnitType;

// 入力受け付けクラス
public class InputReceiver {

    private Scanner  scan     = new Scanner(System.in).useDelimiter("\n");
    private Display  display  = new Display();

    // Start game
    public void start() {
        selectGameMode();
        selectDifficulty();
        Information.init();

        Computer allyCom  = new Computer(UnitType.ALLY);
        Computer enemyCom = new Computer(UnitType.ENEMY);

        while(true) {
            if(Information.judge()) {
                finish();
                break;
            }

            // ally turn
            if(Information.mode.isPvP() || Information.mode.isPvC())
                playerMode(UnitType.ALLY);
            else
                allyCom.start();

            Information.addNotification("");

            // enemy turn
            if(Information.mode.isPvP())
                playerMode(UnitType.ENEMY);
            else
                enemyCom.start();

            Information.resetNotification();
        }
    }

    private void selectGameMode() {
        while(true) {
            display.modeSelection();
            String[] order = scan.next().split(" ");
            switch (order[0]) {
                case "1":
                case "2":
                case "3":
                    Information.setGameMode(order[0]);
                    return;
                default:
            }
        }
    }

    private void selectDifficulty() {
        while(true) {
            display.diffSelection();
            String[] order = scan.next().split(" ");
            switch (order[0]) {
                case "1":
                case "2":
                case "3":
                    Information.setDifficulty(order[0]);
                    return;
                default:
            }
        }
    }

    // When the user plays
    private void playerMode(UnitType type) {
        Unit[] units = type.isAlly() ? Information.allies : Information.enemies;

        Information.addNotification(type.name() + " TURN:");
        selectUnit(units);

        for(Unit unit:units)
            unit.acted = false;
    }

    // Return only when player finishes his turn
    private void selectUnit(Unit[] units) {
        Unit unit;
        while(true) {
            if(Information.judge())
                return;

            display.selection();

            String[] order = scan.next().split(" ");
            switch (order[0]) {
                // units
                case "a":
                case "A":
                    unit = units[0];
                    break;
                case "b":
                case "B":
                    unit = units[1];
                    break;
                case "c":
                case "C":
                    unit = units[2];
                    break;
                // quit game
                case "q":
                    finish();
                    continue;
                // finish
                case "f":
                    return;
                default:
                    continue;
            }

            if(isDisabled(unit))
                continue;

            Field.updateAvailable(unit.y, unit.x, true);
            while(! actuate(unit));
            Field.updateAvailable(unit.y, unit.x, false);
        }
    }

    // return true: action completed / false: action failed
    private boolean actuate(Unit unit) {
        boolean result = false;
        UnitAction action = new UnitAction(unit);

        while(! result) {
            display.action(unit);

            String[] order = scan.next().split(" ");
            switch(order[0]) {
                // up/down/left/right
                case "w":
                case "s":
                case "a":
                case "d":
                    result = action.move(order[0]);
                    break;
                // bomb (x) (y)
                case "b":
                    try {
                        int y = Integer.parseInt(order[2]) - 1;
                        int x = Integer.parseInt(order[1]) - 1;
                        result = action.detonate(y, x);
                    } catch(Exception e) {
                        continue;
                    }
                    break;
                case "c":
                    action.cancel();
                    return true;
                default:
                    continue;
            }
        }

        unit.acted = true;
        return true;
    }

    private void finish() {
        System.out.println();
        if(Information.alliesCount == 0) {
            if(Information.enemiesCount == 0)
                System.out.println("Draw.");
            else
                System.out.println("Enemy wins!");
        } else if(Information.enemiesCount == 0)
            System.out.println("Ally wins!");

        System.out.println("Continue? (y/n)");
        System.out.print("> ");

        String[] order = scan.next().split(" ");
        if(order[0].equals("n")) {
            System.out.println("See you next time!");
            System.exit(0);
        }
    }

    private boolean isDisabled(Unit unit) {
        if(unit.dead) {
            Information.addNotification(String.format("Unit %s is already dead.", unit.character));
            return true;
        }
        if(unit.acted) {
            Information.addNotification(String.format("Unit %s already acted.", unit.character));
            return true;
        }
        return false;
    }
}
