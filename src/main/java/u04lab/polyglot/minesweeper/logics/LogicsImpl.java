package u04lab.polyglot.minesweeper.logics;


import u04lab.polyglot.minesweeper.Pair;
import u04lab.polyglot.minesweeper.grid.Grid;
import u04lab.polyglot.minesweeper.grid.GridImpl;
import u04lab.polyglot.minesweeper.grid.cell.Cell;

import java.util.Set;

public class LogicsImpl implements Logics {

    private final Grid grid;

    public LogicsImpl(int size) {
        this.grid = new GridImpl(size);
    }

    public LogicsImpl(int size, Set<Pair<Integer, Integer>> minePositions) {
        this.grid = new GridImpl(size, minePositions);
    }

    public LogicsImpl(int size, int numberOfMines) {
        this.grid = new GridImpl(size, numberOfMines);
    }

    @Override
    public boolean hit(int x, int y) {
        this.grid.showCell(x, y);
        if (!this.grid.isMine(x, y)) {
            if (this.getAdjacentMinesNumber(x, y) == 0) {
                this.grid.getAdjacent(x, y).forEach(adjacent -> {
                    int adjacentX = adjacent.getX();
                    int adjacentY = adjacent.getY();
                    if (!this.isShown(adjacentX, adjacentY) &&
                            !this.isMine(adjacentX, adjacentY) &&
                            !this.grid.getCell(adjacentX, adjacentY).isFlag()) {
                        this.hit(adjacentX, adjacentY);
                    }
                });
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean isGameWon() {
        return this.grid.getCells().stream()
                .filter(Cell::isMine)
                .anyMatch(cell -> !cell.isShown()) &&
                this.grid.getCells().stream()
                        .filter(cell -> !cell.isMine())
                        .allMatch(Cell::isShown);
    }

    @Override
    public boolean isMine(int x, int y) {
        return this.grid.isMine(x, y);
    }

    @Override
    public boolean isShown(int x, int y) {
        return this.grid.getCell(x, y).isShown();
    }

    @Override
    public int getAdjacentMinesNumber(int x, int y) {
        return this.grid.getAdjacentMines(x, y).size();
    }

    @Override
    public boolean toggleFlag(int x, int y) {
        this.grid.toggleFlag(x, y);
        return this.isFlag(x, y);
    }

    @Override
    public boolean isFlag(int x, int y) {
        return this.grid.getCell(x, y).isFlag();
    }

}
