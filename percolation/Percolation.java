/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private int vtop;
    private int vbot;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentexception("n must be positive");
        }
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = n * i + j;
            }
        }
        uf = new WeightedQuickUnionUF(n * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > grid[0].length || col > grid[0].length) {
            throw new IllegalArgumentException("argument out of range")
        }
        int r = row - 1;
        int c = col - 1;
        if (!isOpen(r, c)) {
            grid[r][c] = 1;
            if (validate(r - 1, c) && isOpen(r - 1, c)) {
                uf.union(grid[r][c], grid[r - 1][c]);
            }
            if (validate(r + 1, c) && isOpen(r + 1, c)) {
                uf.union(grid[r][c], grid[r + 1][c]);
            }
            if (validate(r, c - 1) && isOpen(r, c - 1)) {
                uf.union(grid[r][c], grid[r][c - 1]);
            }
            if (validate(r, c + 1) && isOpen(r, c + 1)) {
                uf.union(grid[r][c], grid[r][c + 1]);
            }
        }
    }

    private boolean validate(int row, int col) {
        int N = grid[0].length;
        if (row <= 0 || col <= 0 || row >= N || col >= N) {
            return false;
        }
        return true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > grid[0].length || col > grid[0].length) {
            throw new IllegalArgumentException("argument out of range")
        }
        return grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > grid[0].length || col > grid[0].length) {
            throw new IllegalArgumentException("argument out of range")
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites()

    // does the system percolate?
    public boolean percolates()

    // test client (optional)
    public static void main(String[] args) {
        StdOut.println("Hello");
    }
}
