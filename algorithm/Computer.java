package algorithm;

import java.util.Random;
import java.lang.Math;
import models.*;
import control.*;
import options.*;
import ui.*;

public class Computer {

    UnitType type;
    Display display = new Display();

    public Computer(UnitType type) {
        this.type = type;
    }

    public void start() {
        Unit[] units = type.isAlly() ? Information.allies : Information.enemies;

        for(Unit unit:units) {
            if(Information.judge())
                return;
            if(unit.dead)
                continue;

            Field.updateAvailable(unit.y, unit.x, true);

            if(bombToAlly(unit))
                System.out.print("");
            else if(moveToClosestAlly(unit))
                System.out.print("");

            Field.updateAvailable(unit.y, unit.x, false);
            display.showInformation();
        }
        sleep(5);
    }

    private boolean bombToAlly(Unit unit){
        UnitAction action = new UnitAction(unit);
        int maxMines = 0;
        int mine_x = 10000;
        int mine_y = 10000;

        Unit[] enemies = type.isAlly() ? Information.enemies : Information.allies;

        for (Unit enemy:enemies){
            if(enemy.dead)
                continue;

            for(int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    if (i != 0 || j != 0){
                        if (InOfField(enemy.x + i, enemy.y + j)){
                            if (getMaxNumberOfMines(enemy.y + j, enemy.x + i) > maxMines){
                                if ((unit.x - Information.AVAILABLE_RANGE) <= (enemy.x + i) && (enemy.x + i) < (unit.x + Information.AVAILABLE_RANGE) && (unit.y - Information.AVAILABLE_RANGE) <= (enemy.y + j) && (enemy.y + j) < (unit.y + Information.AVAILABLE_RANGE)){
                                    maxMines = getMaxNumberOfMines(enemy.y + j, enemy.x + i);
                                    mine_x = enemy.x + i;
                                    mine_y = enemy.y + j;
                                }
                            }
                        }
                    }
                }
            }
        }

        if ((mine_x != 10000) && (Information.fieldmap[mine_y][mine_x].bombed == false)){
            Information.fieldmap[mine_y][mine_x].bombed = true;
            return action.detonate(mine_y, mine_x);
        }
        return false;
    }

    //x, y周囲8マスの数字マスの最大値を取得
    private int getMaxNumberOfMines(int y, int x){
        //数字マスには地雷はない
        if (Information.fieldmap[y][x].surroundMines > 0){
            return 0;
        }

        int numberOfMines = 0;
        for(int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (InOfField(x + i, y + j)){
                    numberOfMines = Math.max(Information.fieldmap[y + j][x + i].surroundMines, numberOfMines);
                }
            }
        }
        return numberOfMines;
    }

    private boolean InOfField(int x, int y){
        if (x < Information.MAX_X && y < Information.MAX_Y && x >= 0 && y >= 0)
            return true;
        else
            return false;
    }

    // 敵ユニットの動き(暫定実装)
    private boolean moveToClosestAlly(Unit unit) {

        int disy = Information.MAX_Y;
        int disx = Information.MAX_X;
        Unit closest;
        UnitAction action = new UnitAction(unit);

        Unit[] enemies = type.isAlly() ? Information.enemies : Information.allies;

        // 一番近いユニットとその距離を計算
        for(Unit enemy:enemies) {
            if(enemy.dead)
                continue;

            int disy2 = enemy.y - unit.y;
            int disx2 = enemy.x - unit.x;
            if(Math.abs(disy2) + Math.abs(disx2) < Math.abs(disy) + Math.abs(disx)) {
                closest = enemy;
                disy = disy2;
                disx = disx2;
            }
        }

        int dirx[] = {1, 0, -1, 0, 1, 0, -1};
        int diry[] = {0, -1, 0, 1, 0, -1, 0};
        String s = "dwasdwas";

        // closestに向かって移動
        if(disx > 0) {
            if (disy > 0){
                for (int i = 3; i < 7; i++){
                    if(InOfField(unit.x + dirx[i], unit.y + diry[i])
                    && !(Information.fieldmap[unit.y + diry[i]][unit.x + dirx[i]] instanceof Mine)
                    && !(Information.fieldmap[unit.y + diry[i]][unit.x + dirx[i]] instanceof Unit)){
                        return action.move(s.substring(i, i + 1));
                    }
                }
            }else{
                for (int i = 0; i < 4; i++){
                    if(InOfField(unit.x + dirx[i], unit.y + diry[i])
                    && !(Information.fieldmap[unit.y + diry[i]][unit.x + dirx[i]] instanceof Mine)
                    && !(Information.fieldmap[unit.y + diry[i]][unit.x + dirx[i]] instanceof Unit)){
                        return action.move(s.substring(i, i + 1));
                    }
                }
            }
        }else{
            if (disy > 0){
                for (int i = 2; i < 6; i++){
                    if(InOfField(unit.x + dirx[i], unit.y + diry[i])
                    && !(Information.fieldmap[unit.y + diry[i]][unit.x + dirx[i]] instanceof Mine)
                    && !(Information.fieldmap[unit.y + diry[i]][unit.x + dirx[i]] instanceof Unit)){
                        return action.move(s.substring(i, i + 1));
                    }
                }
            }else{
                for (int i = 1; i < 5; i++){
                    if(InOfField(unit.x + dirx[i], unit.y + diry[i])
                    && !(Information.fieldmap[unit.y + diry[i]][unit.x + dirx[i]] instanceof Mine)
                    && !(Information.fieldmap[unit.y + diry[i]][unit.x + dirx[i]] instanceof Unit)){
                        return action.move(s.substring(i, i + 1));
                    }
                }
            }
        }

        return action.move("s");
    }

    private void sleep(int sec) {
        try {
            Thread.sleep(sec * 100);
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
