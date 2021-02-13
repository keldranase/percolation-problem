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

public interface IPercolation {

    public void open(int row, int col);

    public boolean isFull(int row, int col);

    public boolean isOpen(int row, int col);

    public boolean percolates();

    public int numberOfOpenSites();
}
