package u04lab.polyglot.minesweeper.grid;


import u04lab.polyglot.minesweeper.Pair;
import u04lab.polyglot.minesweeper.grid.cell.Cell;

import java.util.Set;

public interface Grid {

    Set<Cell> getCells();

    Cell getCell(int i, int j);

    void showCell(int i, int j);

    boolean isMine(int i, int j);

    Set<Pair<Integer, Integer>> getAdjacent(int x, int y);

    Set<Pair<Integer, Integer>> getAdjacentMines(int x, int y);

    void toggleFlag(int x, int y);
}
