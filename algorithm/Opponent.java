package algorithm;

import java.util.Random;
import java.lang.Math;
import models.*;
import control.*;
import options.*;
import ui.*;

public class Opponent {

    UnitType type;
    Display display = new Display();

    public Opponent(UnitType type) {
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
            
            if(bombToAlly(unit)){
                //System.out.print("a");
                System.out.println("");

            }else if(moveToClosestAlly(unit)){
                //System.out.print("b");
                System.out.println("");

            }
            Field.updateAvailable(unit.y, unit.x, false);
            display.showInformation();
        }
        sleep(5);
    }

    private boolean bombToAlly(Unit enemy){
        UnitAction action = new UnitAction(enemy);
        int maxMines = 0;
        //適当なおっきい値(意味はないです)
        int mine_x = 10000;
        int mine_y = 10000;

        Unit[] tekisan = type.isAlly() ? Information.enemies : Information.allies;

        for (Unit ally:tekisan){
            if(ally.dead)
                continue;

            for(int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    if (i != 0 || j != 0){
                        System.out.println((ally.x + i) + " " + (ally.y + j));
                        if (InOfField(ally.x + i, ally.y + j)){
                            if (getMaxNumberOfMines(ally.y + j, ally.x + i) > maxMines){
                                if ((enemy.x - Information.AVAILABLE_RANGE) <= (ally.x + i) && (ally.x + i) < (enemy.x + Information.AVAILABLE_RANGE) && (enemy.y - Information.AVAILABLE_RANGE) <= (ally.y + j) && (ally.y + j) < (enemy.y + Information.AVAILABLE_RANGE)){
                                    maxMines = getMaxNumberOfMines(ally.y + j, ally.x + i);
                                    mine_x = ally.x + i;
                                    mine_y = ally.y + j;
                                    System.out.println("ちんぽ");
                                    //System.out.print(ally.x + " ");
                                    //System.out.println(ally.y);
                                }
                            }
                        }
                    }
                }
            }
        }

        if ((mine_x != 10000) && (Information.fieldmap[mine_y][mine_x].bombed == false)){
            Information.fieldmap[mine_y][mine_x].bombed = true;
            System.out.println("ちんちん");
            System.out.println(enemy.x);
            System.out.println(enemy.y);
            System.out.println(mine_x);
            System.out.println(mine_y);

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
    private boolean moveToClosestAlly(Unit enemy) {

        int disy = Information.MAX_Y;
        int disx = Information.MAX_X;
        Unit closest;
        UnitAction action = new UnitAction(enemy);

        Unit[] tekis = type.isAlly() ? Information.enemies : Information.allies;

        // 一番近いユニットとその距離を計算
        for(Unit ally:tekis) {
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

        int dirx[] = {1, 0, -1, 0, 1, 0, -1};
        int diry[] = {0, -1, 0, 1, 0, -1, 0};
        String s = "dwasdwas";

        // closestに向かって移動
        /*Random rdm = new Random();
        int rnum = rdm.nextInt(2);
        String direction;*/
        if(disx > 0) {
            if (disy > 0){
                for (int i = 3; i < 7; i++){
                    if(InOfField(enemy.x + dirx[i], enemy.y + diry[i]) 
                    && !(Information.fieldmap[enemy.y + diry[i]][enemy.x + dirx[i]] instanceof Mine)
                    && !(Information.fieldmap[enemy.y + diry[i]][enemy.x + dirx[i]] instanceof Unit)){
                        return action.move(s.substring(i, i + 1));
                    }
                }
            }else{
                for (int i = 0; i < 4; i++){
                    if(InOfField(enemy.x + dirx[i], enemy.y + diry[i]) 
                    && !(Information.fieldmap[enemy.y + diry[i]][enemy.x + dirx[i]] instanceof Mine)
                    && !(Information.fieldmap[enemy.y + diry[i]][enemy.x + dirx[i]] instanceof Unit)){
                        return action.move(s.substring(i, i + 1));
                    }
                }
            }
        }else{
            if (disy > 0){
                for (int i = 2; i < 6; i++){
                    if(InOfField(enemy.x + dirx[i], enemy.y + diry[i]) 
                    && !(Information.fieldmap[enemy.y + diry[i]][enemy.x + dirx[i]] instanceof Mine)
                    && !(Information.fieldmap[enemy.y + diry[i]][enemy.x + dirx[i]] instanceof Unit)){
                        return action.move(s.substring(i, i + 1));
                    }
                }
            }else{
                for (int i = 1; i < 5; i++){
                    if(InOfField(enemy.x + dirx[i], enemy.y + diry[i]) 
                    && !(Information.fieldmap[enemy.y + diry[i]][enemy.x + dirx[i]] instanceof Mine)
                    && !(Information.fieldmap[enemy.y + diry[i]][enemy.x + dirx[i]] instanceof Unit)){
                        return action.move(s.substring(i, i + 1));
                    }
                }
            }
        }

        /*String direction;
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
        }*/

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
