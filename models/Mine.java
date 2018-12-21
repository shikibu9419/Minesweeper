package models;

import system.*;

// 地雷マス
public class Mine extends Cell {
    public Mine(int y, int x) {
        setCoordinate(y, x);
    }

    // Bomb!!!!!!!!!!!!!!!!!!!!!!
    public void bomb() {
        Field.fieldmap[this.y][this.x] = new Flatland();

        // Surrounding field becomes Flatland.
        for(int i = 0; i < 8; i++) {
            int x2 = this.x + Utils.dx[i];
            int y2 = this.y + Utils.dy[i];

            if(Utils.outOfField(y2, x2))
                continue;

            Cell cell = Field.fieldmap[y2][x2];
            if(cell instanceof Flatland)
                ((Flatland) cell).surroundingBombs--;
            else if(cell instanceof Mine)
                ((Mine) cell).bomb();
            else if(cell instanceof Unit){
                ((Unit) cell).death();
            }
        }
    }

    public char getChar() {
        return 'X';
    }
}
