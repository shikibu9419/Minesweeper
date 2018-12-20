package ui;

import java.util.*;

public class Field {
    public int NUM_X = 20;
    public int NUM_Y = 20;
    public char[][] fieldmap = new char[NUM_Y][NUM_X];

    public Field() {
        init();
    }

    private void init() {
        for(int i = 0; i < NUM_Y; i++)
            Arrays.fill(fieldmap[i], '.');

        fieldmap[1][0] = 'x';
        fieldmap[1][2] = 'x';
    }
}
