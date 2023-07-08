/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private int trials;

    private double[] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        fractions = new double[trials];

        for (int count = 0; count < trials; ++count) {

            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int i = StdRandom.uniformInt(1, n + 1);
                int j = StdRandom.uniformInt(1, n + 1);

                percolation.open(i, j);
            }

            System.out.println(percolation.numberOfOpenSites());
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return 1.1;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 1.1;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 1.1;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 1.1;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats a = new PercolationStats(10, 5);
    }

}
