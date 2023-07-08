/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private int top = 0;
    private int bottom;
    private int size;
    private int openSitesNumber = 0;
    private WeightedQuickUnionUF unionFind;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("grid size must be more than 0 ! ");
        }

        this.size = n;
        this.grid = new int[n][n];
        this.bottom = size * size + 1;
        this.unionFind = new WeightedQuickUnionUF(n * n + 2);
    }

    private int getComponents() {
        return unionFind.count();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        int siteIndex = getUnionFindId(row, col);
        validateSite(row, col);
        if (grid[row - 1][col - 1] == 1) return;
        grid[row - 1][col - 1] = 1;
        openSitesNumber++;

        if (row == 1) unionFind.union(siteIndex, top);
        if (row == size) unionFind.union(siteIndex, bottom);

        // left

        if (col > 1 && isOpen(row, col - 1))
            unionFind.union(siteIndex, getUnionFindId(row, col - 1));

        // right

        if (col < size && isOpen(row, col + 1))
            unionFind.union(siteIndex, getUnionFindId(row, col + 1));

        // top

        if (row > 1 && isOpen(row - 1, col))
            unionFind.union(siteIndex, getUnionFindId(row - 1, col));

        // bottom

        if (row < size && isOpen(row + 1, col))
            unionFind.union(siteIndex, getUnionFindId(row + 1, col));

    }


    private int getUnionFindId(int row, int col) {
        validateSite(row, col);
        return (row - 1) * this.size + col;
    }

    private void validateSite(int row, int col) {
        if (row <= 0 || row > this.size || col <= 0 || col > this.size) {
            throw new IllegalArgumentException("Must be between 0 and " + this.size);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return unionFind.connected(getUnionFindId(row, col), top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSitesNumber;
    }

    public void printPercolation() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                System.out.print(this.grid[i][j]);
            }
            System.out.println(" ");
        }
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.connected(this.top, this.bottom);
    }

    public static void main(String[] args) {
    }

}
