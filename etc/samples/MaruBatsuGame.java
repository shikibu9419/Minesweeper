import java.util.Scanner;
 
public class MaruBatsuGame {
        public static void main(String[] args) {
                Game game = new Game();
                new GameControl(game).startGame();
        }
}
 
class GameControl {
        private Game game;
       
        private Scanner scanner = new Scanner(System.in);
       
        public GameControl(Game game) {
                this.game = game;
        }
       
        public void startGame() {
                MaruBatsu win;
               
                MaruBatsu turn = MaruBatsu.MARU;
               
                while( (win = game.checkWin()) == null && win != MaruBatsu.NONE ) {
                       
                        System.out.println(game);
                       
                        while (!input(turn)){
                                System.out.println();
                                System.out.println(game);
                        };
                       
                        // 交互に順番を回す
                        if( turn == MaruBatsu.MARU )
                                turn = MaruBatsu.BATSU;
                        else
                                turn = MaruBatsu.MARU;
                }
               
                System.out.println(game);
                System.out.println();
               
                if( win == MaruBatsu.NONE ) {
                        System.out.println("引き分け！");
                } else {
                        System.out.println(win + "の勝利！");
                }
                System.out.println("ゲームを終了します");
               
                scanner.close();
        }
       
        private boolean input(MaruBatsu maruBatsu) {
                System.out.println("["+maruBatsu+"の番]");
                System.out.println("縦と横のインデックスを半角スペース区切りで指定してください。");
                System.out.print(">");
               
                String line = scanner.nextLine();
               
                String[] nums = line.split(" ");
               
                if( nums.length != 2 ) {
                        System.out.println("!!!");
                        System.out.println("整数は２つ入力してください。");
                        System.out.println("!!!");
                       
                        return false;
                }
               
                int colIndex = 0;
                int rowIndex = 0;
               
                try {
                        colIndex = Integer.parseInt(nums[0]);
                        rowIndex = Integer.parseInt(nums[1]);
                       
                        if( colIndex <= -1 || colIndex >= 3
                                        || rowIndex <= -1 || rowIndex >= 3 ) {
                                System.out.println("!!!");
                                System.out.println("整数は0 ～2の間で指定してください。");
                                System.out.println("!!!");
                                return false;
                        }
                       
                } catch ( NumberFormatException e ) {
                        System.out.println("!!!");
                        System.out.println("整数を入力しください。");
                        System.out.println("!!!");
                        return false;
                }
               
                if( game.squares[colIndex][rowIndex] != MaruBatsu.NONE ) {
                        System.out.println("!!!");
                        System.out.println("その場所には置けません。");
                        System.out.println("!!!");
                        return false;
                }
               
                game.put(colIndex, rowIndex, maruBatsu);
               
                System.out.println();
               
                return true;
        }
}
 
enum MaruBatsu {
        MARU, BATSU, NONE;
 
        public String toString() {
                switch (this) {
                case MARU:
                        return "○";
                case BATSU:
                        return "☓";
                case NONE:
                        return "　";
                }
 
                throw new RuntimeException();
        }
}
 
class Game {
        MaruBatsu[][] squares = new MaruBatsu[3][3];
 
        public Game() {
                for (int i = 0; i < squares.length; i++) {
                        for (int j = 0; j < squares.length; j++) {
                                squares[i][j] = MaruBatsu.NONE;
                        }
                }
        }
 
        public void put(int colIndex, int rowIndex, MaruBatsu maruBatsu) {
                squares[colIndex][rowIndex] = maruBatsu;
        }
       
        // ○が勝利していれば、MaruBatsu.MARU
        // ☓が勝利していれば、MaruBatsu.BATSU
        // 引き分けならば、MaruBatsu.NONE
        // まだ勝敗が決まっていなければ、null をそれぞれ返す。
        public MaruBatsu checkWin() {
                //縦
                for(int i=0;i<3;i++) {
                        MaruBatsu win = check( squares[i] );
                        if( win != null) return win;
                }
                       
                //横
                for(int i=0;i<3;i++) {
                        MaruBatsu win = check( new MaruBatsu[] {squares[0][i],squares[1][i],squares[2][i]} );
                        if( win != null ) return win;
                }
               
                //斜め
                MaruBatsu win = check( new MaruBatsu[]{squares[0][0], squares[1][1], squares[2][2]});
                if( win != null ) return win;
                win = check( new MaruBatsu[]{squares[2][0], squares[1][1], squares[0][2]});
                if( win != null ) return win;
               
                //全部埋まっていれば、引き分け
                for(int i=0; i<squares.length;i++) {
                        for(int j=0;j<squares[i].length;j++) {
                                if ( squares[i][j] == MaruBatsu.NONE )
                                        return null;
                        }
                }
               
                return MaruBatsu.NONE;
        }
       
        // 一列全部そろっているかチェック
        private MaruBatsu check(MaruBatsu[] array) {
                if( array[0] == MaruBatsu.MARU && array[1] == MaruBatsu.MARU && array[2] == MaruBatsu.MARU )
                        return MaruBatsu.MARU;
                if( array[0] == MaruBatsu.BATSU && array[1] == MaruBatsu.BATSU && array[2] == MaruBatsu.BATSU )
                        return MaruBatsu.BATSU;
               
                return null;
        }
        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();
               
                sb.append("　");
                sb.append("０");sb.append("１");sb.append("２");
                sb.append(System.lineSeparator());
               
                for (int i = 0; i < squares.length; i++) {
                        sb.append( hanNum2ZenNum(Integer.toString(i)) );
                        for (int j = 0; j < squares[i].length; j++) {
                                sb.append(squares[j][i]);
                        }
                        sb.append(System.lineSeparator());
                }
 
                return sb.toString();
        }
 
        /**
         * 全角数字を半角に変換します。
         */
        public static String hanNum2ZenNum(String s) {
                StringBuffer sb = new StringBuffer(s);
                for (int i = 0; i < s.length(); i++) {
 
                        char c = s.charAt(i);
                        if (c >= '0' && c <= '9') {
 
                                sb.setCharAt(i, (char) (c - '0' + '０'));
                        }
                }
                return sb.toString();
        }
}
