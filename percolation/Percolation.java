/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int nopen;
    private final WeightedQuickUnionUF uf;
    private final int vtop;
    private final int vbot;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        vbot = n * n + 1;
        vtop = 0;
        nopen = 0;
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
                if (i == 0) {
                    uf.union(index(i + 1, j + 1), vtop);
                }
                if (i + 1 == n) {
                    uf.union(index(i + 1, j + 1), vbot);
                }
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = 1;
            nopen++;
            if (validate(row - 1, col)) {
                if (isOpen(row - 1, col))
                    uf.union(index(row, col), index(row - 1, col));
            }
            if (validate(row + 1, col)) {
                if (isOpen(row + 1, col))
                    uf.union(index(row, col), index(row + 1, col));
            }
            if (validate(row, col - 1)) {
                if (isOpen(row, col - 1))
                    uf.union(index(row, col), index(row, col - 1));
            }
            if (validate(row, col + 1)) {
                if (isOpen(row, col + 1))
                    uf.union(index(row, col), index(row, col + 1));
            }
        }
    }

    private boolean validate(int row, int col) {
        int N = grid[0].length;
        int r = row - 1;
        int c = col - 1;
        if (r < 0 || c < 0 || r >= N || c >= N) {
            return false;
        }
        return true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException("Cant' index [" + row + "][" + col + "]");
        }
        return grid[row - 1][col - 1] == 1;
    }

    private int index(int row, int col) {
        return grid[0].length * (row - 1) + col;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException("Cant' index [" + row + "][" + col + "]");
        }
        return uf.find(vtop) == uf.find(index(row, col)) && isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nopen;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(vbot) == uf.find(vtop);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // n-by-n percolation system
        Percolation perc = new Percolation(n);

        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(
                "The system " + (perc.percolates() ? "percolates" : "does not percolate"));
    }
}
