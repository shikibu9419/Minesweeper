package algorithm;

import java.util.Random;
import java.lang.Math;
import models.*;
import control.*;

public class Opponent {

    public Opponent() {
        super();
    }

    public void start() {
        for(Unit enemy:Information.enemies) {
            if(enemy.dead)
                continue;

            if(bombToAlly(enemy)){
                System.out.println("");
            }else if(moveToClosestAlly(enemy)){
                System.out.println("");
            }
        }
    }

    private boolean bombToAlly(Unit enemy){


        UnitAction action = new UnitAction(enemy);
        int maxMines = 0;
        //適当なおっきい値(意味はないです)
        int mine_x = 10000;
        int mine_y = 10000;

        for (Unit ally:Information.allies){
            if(ally.dead)
                continue;

            for(int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    if (i != 0 || j != 0){
                        if ((ally.x + i) < Information.MAX_X && (ally.y + j) < Information.MAX_Y && (ally.x + i) >= 0 && (ally.y + j) >= 0){
                            if (getMaxNumberOfMines(ally.y + j, ally.x + i) > maxMines){
                                if ((enemy.x - Information.AVAILABLE_RANGE) <= (ally.x + i) && (ally.x + i) < (enemy.x + Information.AVAILABLE_RANGE) && (enemy.y - Information.AVAILABLE_RANGE) <= (ally.y + j) && (ally.y + j) < (enemy.y + Information.AVAILABLE_RANGE)){
                                    maxMines = getMaxNumberOfMines(ally.y + j, ally.x + i);
                                    mine_x = ally.x + i;
                                    mine_y = ally.y + j;
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
                if ((x + i) < Information.MAX_X && (y + j) < Information.MAX_Y && (x + i) >= 0 && (y + j) >= 0){
                    numberOfMines = Math.max(Information.fieldmap[y + j][x + i].surroundMines, numberOfMines);
                }
            }
        }
        return numberOfMines;
    }

    // 敵ユニットの動き(暫定実装)
    private boolean moveToClosestAlly(Unit enemy) {

        int disy = Information.MAX_Y;
        int disx = Information.MAX_X;
        Unit closest;
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
        Random rdm = new Random();
        int rnum = rdm.nextInt(2);
        System.out.println(rnum);
        String direction;
        if(disx > 0) {
            if (disy > 0){
                if (rnum > 0)
                    direction = "s";
                else
                    direction = "d";
            }else{
                if (rnum > 0)
                    direction = "d";
                else
                    direction = "w";
            }
        }else{
            if (disy > 0){
                if (rnum > 0){
                    direction = "a";
                }else{
                    direction = "s";
                }
            }else{
                if (rnum > 0){
                    direction = "a";
                }else{
                    direction = "w";
                }
            }
        }
        return action.move(direction);
    }

}
