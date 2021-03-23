package sut.cselp.jcourse2019.spring.akozyrev;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Task_01 {

    public static void main(String[] args) {
        final int DIMENSION_1 = 700;
        final int DIMENSION_2 = 1200;

        StdDraw.setCanvasSize(1200, 700);
        StdDraw.setXscale(0, 1199);
        StdDraw.setYscale(0, 699);
        ConwayLife universe = new ConwayLife(DIMENSION_1, DIMENSION_2);

        {
/*            for (int i = 0; i < DIMENSION_1; i++) {
                for (int j = 0; j < DIMENSION_2; j++) {
                    universe.field[i][j] = (int) Math.round(Math.random());
                }
            }*/

            universe.field[0][1] = 1;
            universe.field[1][2] = 1;
            universe.field[2][0] = 1;
            universe.field[2][1] = 1;
            universe.field[2][2] = 1;
            /*universe.field[0][DIMENSION_2 - 2] = 1;
            universe.field[1][DIMENSION_2 - 1] = 1;
            universe.field[2][DIMENSION_2 - 4] = 1;
            universe.field[2][DIMENSION_2 - 3] = 1;
            universe.field[2][DIMENSION_2 - 2] = 1;*/

            universe.field[DIMENSION_1 - 2][DIMENSION_2 - 3] = 1;
            universe.field[DIMENSION_1 - 1][DIMENSION_2 - 2] = 1;
            universe.field[DIMENSION_1 - 3][DIMENSION_2 - 3] = 1;
            universe.field[DIMENSION_1 - 3][DIMENSION_2 - 2] = 1;
            universe.field[DIMENSION_1 - 3][DIMENSION_2 - 1] = 1;

            /*universe.field[DIMENSION_1 - 3][1] = 1;
            universe.field[DIMENSION_1 - 2][2] = 1;
            universe.field[DIMENSION_1 - 1][0] = 1;
            universe.field[DIMENSION_1 - 1][1] = 1;
            universe.field[DIMENSION_1 - 1][2] = 1;*/
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.BLACK);

        while (true) {
            universe.drawField();
            //universe.drawGrid();
            StdDraw.show();
            //StdDraw.pause(1);
            universe.setNextGen();
        }

    }
}
