package algorithm;

import java.lang.Math;
import models.*;
import control.*;

public class Opponent extends Algorithm {

    public Opponent() {
        super();
    }

    public void start() {
        Information.addNotification("OPPONENT'S TURN:");

        for(Unit enemy:Information.enemies) {
            if(enemy.dead)
                continue;
            if(moveToClosestAlly(enemy))
                System.out.println("");
        }

        Information.addNotification("");
    }

    // 敵ユニットの動き(暫定実装)
    private boolean moveToClosestAlly(Unit enemy) {
        Unit closest;
        int disy = Information.MAX_Y;
        int disx = Information.MAX_X;
        UnitAction action = new UnitAction(enemy);

        // 一番近いユニットとその距離を計算
        for(Unit ally:Information.allies) {
            if(ally.dead)
                continue;

            int disy2 = ally.y - enemy.y;
            int disx2 = ally.x - enemy.x;
            if(Math.abs(disy2) + Math.abs(disx2) < Math.abs(disy) + Math.abs(disx)) {
                closest = ally;
                disy = disy2;
                disx = disx2;
            }
        }

        // closestに向かって移動
        String direction;
        if(disy > disx) {
            if(disx > 0)
                direction = "d";
            else
                direction = "a";
        } else {
            if(disy > 0)
                direction = "s";
            else
                direction = "w";
        }

        return action.move(direction);
    }
}
