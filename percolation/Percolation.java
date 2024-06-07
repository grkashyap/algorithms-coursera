import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int TOP = 0;
    private final boolean[][] opened;
    private final int size;
    private final int bottom;
    private int openSites;
    private final WeightedQuickUnionUF qf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) {
            throw new IndexOutOfBoundsException();
        }

        size = n;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        isInBounds(row, col);
        opened[row - 1][col - 1] = true;
        openSites += 1;

        if (row == 1) {
            qf.union(convert2dTo1dCord(row, col), TOP);
        }

        if (row == size) {
            qf.union(convert2dTo1dCord(row, col), bottom);
        }

        if (row > 1 && isOpen(row - 1, col)) {
            qf.union(convert2dTo1dCord(row, col), convert2dTo1dCord(row - 1, col));
        }

        if (row < size && isOpen(row + 1, col)) {
            qf.union(convert2dTo1dCord(row, col), convert2dTo1dCord(row + 1, col));
        }

        if (col < 1 && isOpen(row, col - 1)) {
            qf.union(convert2dTo1dCord(row, col), convert2dTo1dCord(row, col - 1));
        }

        if (col < size && isOpen(row, col + 1)) {
            qf.union(convert2dTo1dCord(row, col), convert2dTo1dCord(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isInBounds(row, col);

        return opened[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        isInBounds(row, col);
        return qf.find(TOP) == qf.find(convert2dTo1dCord(row, col));

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.find(TOP) == qf.find(bottom);
    }

    private void isInBounds(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int convert2dTo1dCord(int row, int col) {
        return size * (row - 1) + col;
    }
}