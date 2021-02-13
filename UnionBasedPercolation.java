/* *****************************************************************************
 *  Name:    Ada Lovelace
 *  NetID:   alovelace
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class UnionBasedPercolation implements IPercolation {

    private WeightedQuickUnionUF unionFind;
    private int n;
    private int numOfCells;

    public static void main(String[] args) {

    }

    public UnionBasedPercolation(int nIn) {
        n = nIn;
        numOfCells = n * n;
        unionFind = new WeightedQuickUnionUF(numOfCells
                                                     + 2); // [0;n^2) - values for cells. 3 more values for keeping track of where each cell belongs
    }

    public void open(int row, int col) {
        int num = getNum(row, col);
        unionFind.union(num, numOfCells + 1);
    }

    public boolean isFull(int row, int col) {
        int num = getNum(row, col);
        if (unionFind.connected(num, numOfCells)) {
            return true;
        }

        return false;
    }

    public boolean isOpen(int row, int col) {
        int num = getNum(row, col);
        if (unionFind.find(num) == num) {
            return false;
        }

        return true;
    }

    public boolean percolates() {
        return false;
    }

    public int numberOfOpenSites() {
        return 0;
    }

    private int getNum(int row, int col) {
        return (n * row) + col;
    }
}
