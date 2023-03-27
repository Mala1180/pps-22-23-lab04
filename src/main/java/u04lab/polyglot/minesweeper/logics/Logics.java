package u04lab.polyglot.minesweeper.logics;

public interface Logics {

    /**
     * @param x row
     * @param y column
     * @return false if the cell is empty, true if it is a mine
     */
    boolean hit(int x, int y);

    /**
     * @return true if all cells except mines are shown, false otherwise
     */
    boolean isGameWon();

    boolean isMine(int x, int y);

    boolean isShown(int x, int y);

    int getAdjacentMinesNumber(int x, int y);

    boolean toggleFlag(int x, int y);

    boolean isFlag(int x, int y);
}
