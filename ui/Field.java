package ui;

import java.util.*;
import system.*;

public class Field {
    public int NUM_X = 20;
    public int NUM_Y = 20;
    public Cell[][] fieldmap = new Cell[NUM_Y][NUM_X];

    public Field() {
        init();
    }

    private void init() {
        for(int i = 0; i < NUM_Y; i++)
            for(int j = 0; j < NUM_X; j++)
                fieldmap[i][j] = new Flatfield();
    }
}
