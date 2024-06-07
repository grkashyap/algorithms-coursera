import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int t;
    private final double[] threshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1)
        {
            throw new IllegalArgumentException();
        }
        t = trials;
        threshold = new double[t];

        for (int i = 0; i < threshold.length; i++)
        {
            threshold[i] = calcThreshold(n);
        }
    }

    private double calcThreshold(int n)
    {
        int openSites = 0;
        Percolation pr = new Percolation(n);
        while (!pr.percolates())
        {
            int i = StdRandom.uniformInt(n)+1;
            int j = StdRandom.uniformInt(n)+1;
            if (!pr.isOpen(i, j))
            {
                pr.open(i, j);
                openSites++;
            }
        }
        return (double) openSites / (n*n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev())/(Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev())/(Math.sqrt(t));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, t);

        String confidence = ps.confidenceHi() + ", " + ps.confidenceLo();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% Confidence Interval = " + confidence);
    }
}