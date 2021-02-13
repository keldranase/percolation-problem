/******************************************************************************
 *  Name:    Kevin Wayne
 *  NetID:   wayne
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Modeling Percolation using an N-by-N grid and Union-Find data
 *                structures to determine the threshold. woot. woot.
 ******************************************************************************/

import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation implements IPercolation {

    private int[] arr;  // something
    private int n;  // something
    private int numberOfOpenSites = 0; // something
    private QuickUnionUF unionFind;

    // Ctor
    public Percolation(int n) {
        this.n = n;
        arr = new int[n * n];

        unionFind = new QuickUnionUF(arr.length + 2);

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = 0;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int pos = getPos(row, col);

        if (arr[pos] == 0) {
            numberOfOpenSites++;
            arr[pos] = 1;

            if (!checkTopOrBot(pos)) {
                checkArond(pos);
            }
        }
    }

    private void checkArond(int pos) {
        int top = pos - n;
        int bot = pos + n;
        int left = pos - 1;
        int right = pos + 1;

        if (isFull(top) || isFull(bot) || isFull(left) || isFull(right)) {
            floatCell(pos);
        }
    }

    private void floatNearestIfOpen(int pos) {
        int top = pos - n;
        int bot = pos + n;
        int left = pos - 1;
        int right = pos + 1;

        // if (arr[top] == 1 && !isFull(top)) {
        //     floatCell(top);
        // }
        if (isValid(bot)) {
            if (arr[bot] == 1 && !isFull(bot)) {
                floatCell(bot);
            }
        }
        if (isValid(left)) {
            if (arr[left] == 1 && !isFull(left)) {
                floatCell(left);
            }
        }
        if (isValid(right)) {
            if (arr[right] == 1 && !isFull(right)) {
                floatCell(right);
            }
        }
    }


    private boolean checkTopOrBot(int pos) {
        if (pos < n) {
            //unionFind.union(pos + 1, 0);
            floatCell(pos);
            return true;
        }
        if (pos >= arr.length - n) {
            unionFind.union(pos + 1, arr.length + 1);
            checkArond(pos);

            return true;
        }
        return false;
    }

    private void floatCell(int pos) {
        unionFind.union(pos + 1, 0);

        floatNearestIfOpen(pos);
    }

    private boolean isValid(int pos) {
        //StdOut.println("The pos:: {0} is {1}" + pos + (pos > 0 && pos < arr.length));

        return pos > 0 && pos < arr.length;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int pos = getPos(row, col);

        if (arr[pos] == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int pos = getPos(row, col);

        if (!isValid(pos)) {
            return false;
        }

        if (unionFind.find(pos + 1) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isFull(int pos) {
        if (!isValid(pos)) {
            return false;
        }
        if (unionFind.find(pos + 1) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    // asdf
    private int getPos(int row, int col) {
        return (n * row) + col;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {

        return unionFind.connected(0, arr.length + 1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
