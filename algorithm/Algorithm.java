package algorithm;

import control.Information;
import models.Cell;

// algorithmパッケージのメインクラス
public class Algorithm {

    protected Cell[][] fieldmap;
    protected int MAX_X;
    protected int MAX_Y;

    public Algorithm() {
        fieldmap = Information.fieldmap;
        MAX_Y = fieldmap.length;
        MAX_X = fieldmap[0].length;
    }
}
