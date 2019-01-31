package ui;

import java.util.*;
import algorithm.*;
import control.*;
import models.*;

// 入力受け付けクラス
public class InputReceiver {

    private Scanner  scan    = new Scanner(System.in).useDelimiter("\n");
    private Display  display = new Display();
    private Opponent ally    = new Opponent();
    private Opponent enemy   = new Opponent();

    // ゲーム開始
    public void start() {
        selectGameMode();
        selectDifficulty();
        Information.init();

        while(true) {
            if(judge()) {
                finish();
                break;
            }

            // ally turn
            if(Information.mode.isPvP() || Information.mode.isPvC())
                playerMode(UnitType.ALLY);
            else
                ally.start();

            Information.addNotification("");

            // enemy turn
            if(Information.mode.isPvP())
                playerMode(UnitType.ENEMY);
            else
                enemy.start();

            Information.resetNotification();
        }
    }

    // select game mode
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

    // select difficulty
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

    // プレイヤーが動かす
    private void playerMode(UnitType type) {
        Unit[] units = type.isAlly() ? Information.allies : Information.enemies;

        Information.addNotification(type.name() + " TURN:");
        selectUnit(units);

        for(Unit unit:units)
            unit.acted = false;
    }

    // プレイヤーターン終了時にreturn
    private void selectUnit(Unit[] units) {
        Unit unit;
        while(true) {
            if(judge())
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
                // finish
                case "f":
                    return;
                default:
                    continue;
            }

            if(isDisabled(unit))
                continue;

            unit.updateAvailable(true);
            while(! actuate(unit));
            unit.updateAvailable(false);
        }
    }

    // true: 行動完了, false: 行動失敗
    private boolean actuate(Unit unit) {
        boolean result = false;
        UnitAction action = new UnitAction(unit);

        while(! result) {
            display.action(unit);

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
                    if(order.length >= 3) {
                        int y = Integer.parseInt(order[2]) - 1;
                        int x = Integer.parseInt(order[1]) - 1;
                        result = action.detonate(y, x);
                    }
                    break;
                case "c":
                    action.cancel();
                    return true;
                default:
                    continue;
            }
        }

        // この時resultはtrue
        unit.acted = result;
        return result;
    }

    private boolean judge() {
        return Information.alliesCount == 0 || Information.enemiesCount == 0;
    }

    private void finish() {
        if(Information.alliesCount == 0)
            System.out.println("Enemy wins!");
        else if(Information.enemiesCount == 0)
            System.out.println("Ally wins!");
        else
            System.out.println("");

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
