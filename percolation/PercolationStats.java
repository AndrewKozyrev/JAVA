/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] x;
    private final int T;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive.");
        if (trials <= 0)
            throw new IllegalArgumentException("Number os trials has to be greater than 0.");
        this.T = trials;
        x = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int column = StdRandom.uniform(1, n + 1);
                perc.open(row, column);
            }
            x[i] = (double) perc.numberOfOpenSites() / (n * n);
            perc = null;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(T);
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 2)
            throw new RuntimeException("This program takes two command-line arguments: n and T.");
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        Stopwatch time = new Stopwatch();
        PercolationStats experiment = new PercolationStats(n, T);
        double elapsed = time.elapsedTime();
        StdOut.println("mean \t\t = " + experiment.mean());
        StdOut.println("stddev \t\t = " + experiment.stddev());
        StdOut.println(
                "95% confidence interval \t\t = [" + experiment.confidenceLo() + ", " + experiment
                        .confidenceHi() + "]");
        StdOut.println("Elapsed time \t\t = " + elapsed);
    }
}
