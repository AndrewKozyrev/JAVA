package sut.cselp.jcourse2019.spring.akozyrev;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Arrays;

public class ConwayLife {
    int RowDim;
    int ColDim;
    int[][] field;

    public ConwayLife(int rowDim, int colDim) {
        RowDim = rowDim;
        ColDim = colDim;
        field = new int[RowDim][ColDim];
    }

    public void setField(int[][] given) {
        this.field = given;
    }


    /**
     * Этот метод рисует текущее состояние поля, функции StdDraw:: DoubleBuffering позволяет сначала рисовать
     * изображение в буфер (скорее всего через какие-то быстрые регистры), а затем только выводит весь
     * рисунок на экран. В результате получается рисовать на порядки быстрее
     */
    public void drawField() {
        double xScale = 1200.0 / ColDim;
        double yScale = 700.0 / RowDim;
        double radius = Math.cbrt(1200.0 * 700 / (RowDim * ColDim));
        StdDraw.clear();
        for (int i = 0; i < RowDim; i++) {
            for (int j = 0; j < ColDim; j++) {
                if (field[i][j] == 1) {
                    StdDraw.filledCircle(j * xScale, i * yScale, radius);
                }
            }
        }
    }

    public void displayField() {
        System.out.println(Arrays.deepToString(this.field));
    }

    public void setNextGen() {

        int[][] NextField = new int[field.length][field[0].length];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int neighbour = 0;
                neighbour = countNeighbours(i, j);

                switch (neighbour) {
                    case 0:
                        NextField[i][j] = 0;
                        break;

                    case 1:
                        NextField[i][j] = 0;
                        break;

                    case 2:
                        if (this.getCellStatus(i, j)) {
                            NextField[i][j] = 1;
                        } else {
                            NextField[i][j] = 0;
                        }
                        break;

                    case 3:
                        NextField[i][j] = 1;
                        break;

                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        NextField[i][j] = 0;
                        break;

                    default:
                        throw new RuntimeException("Impossible number of neighbours.");
                }
            }
        }

        field = NextField;
    }

    public boolean getCellStatus(int row, int column) {
        if (this.field[row][column] == 1)
            return true;
        else
            return false;
    }

    public int countNeighbours(int row, int column) {
        int count = 0;
        int rowUp = row - 1;
        int rowDown = row + 1;
        int columnLeft = column - 1;
        int columnRight = column + 1;

        if (row == 0) {
            rowUp = field.length - 1;
        }
        if (row == field.length - 1) {
            rowDown = 0;
        }
        if (column == 0) {
            columnLeft = field[row].length - 1;
        }
        if (column == field[row].length - 1) {
            columnRight = 0;
        }

        count += field[rowUp][column];
        count += field[rowUp][columnLeft];
        count += field[rowUp][columnRight];
        count += field[row][columnLeft];
        count += field[row][columnRight];
        count += field[rowDown][columnLeft];
        count += field[rowDown][columnRight];
        count += field[rowDown][column];

        return count;
    }

    public void drawGrid() {
        double xScale = 1200.0 / ColDim;
        double yScale = 700.0 / RowDim;
        double radius = Math.cbrt(2 * 1200.0 * 700 / (RowDim * ColDim));

        StdDraw.setPenColor(Color.red);
        for (int i = 0; i < RowDim; i++) {
            for (int j = 0; j < ColDim; j++) {
                StdDraw.square(j * xScale, i * yScale, radius);
            }
        }
    }
}
