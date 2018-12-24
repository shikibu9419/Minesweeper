package control;

import java.util.*;

// controlパッケージのメインクラス
public class Control {
    private static Random rand = new Random();
    private static int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};
    private static int[] dx = {-1, -1, -1,  0, 0,  1, 1, 1};

    // 周りのfield範囲内の座標を[y][x]の配列にして返す
    public static int[][] surroundingField(int y, int x) {
        ArrayList<int[]> surround = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            int y2 = y + dy[i];
            int x2 = x + dx[i];
            if (outOfField(y2, x2))
                continue;

            int[] yx = {y2, x2};
            surround.add(yx);
        }

        int[][] res = new int[surround.size()][2];
        for(int i = 0; i < surround.size(); i++) {
            res[i] = surround.get(i);
        }

        return res;
    }

    // fieldの外の座標ならtrueを返す
    public static boolean outOfField(int y, int x) {
        return (x < 0 || x >= Field.MAX_X || y < 0 || y >= Field.MAX_Y);
    }

    // max未満のランダムな自然数を返す
    public static int randomInt(int max) {
        return rand.nextInt(max);
    }

    // ゲームの終了
    public static void exitGame() {
        System.out.println("Good bye!");
        System.exit(0);
    }

    ////// 多分あとで消すとても汚いメソッド
    public static void goal() {
        // clearScreenパクリ
        try {
            new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
        } catch(java.io.IOException e) {
            System.out.println("IO Error occurred.");
            System.exit(1);
        } catch(InterruptedException e) {
            System.out.println("I.R. Error occurred.");
            System.exit(1);
        }

        // displayFieldパクリ
        // xの目盛表示
        for(int i = 0; i <= Field.MAX_X; i++)
            System.out.printf("%2d ", i);
        System.out.println("");

        for(int i = 0; i < Field.MAX_Y; i++) {
            // yの目盛表示
            System.out.printf("%2d ", i + 1);
            // fieldの表示
            for(int j = 0; j < Field.MAX_X; j++) {
                // 特別な表示
                if(Field.fieldmap[i][j] instanceof models.Mine)
                    System.out.print(" " + 'X' + " ");
                else if((Field.fieldmap[i][j] instanceof models.Flatland) && ((models.Flatland) Field.fieldmap[i][j]).surroundingBombs > 0) {
                    System.out.print(" " + ((models.Flatland) Field.fieldmap[i][j]).surroundingBombs + " ");
                }
                else
                    System.out.print(" " + Field.fieldmap[i][j].getChar() + " ");
            }
            System.out.println("");
        }

        // ゲームクリアだぜ
        System.out.println("");
        System.out.println("GAME CLEAR!!");
        exitGame();
    }
}
