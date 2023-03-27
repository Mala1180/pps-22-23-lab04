package u04lab.polyglot.minesweeper.grid;

import u04lab.polyglot.minesweeper.Pair;
import u04lab.polyglot.minesweeper.grid.cell.Cell;
import u04lab.polyglot.minesweeper.grid.cell.CellImpl;
import u04lab.polyglot.minesweeper.grid.cell.CellType;

import java.util.*;
import java.util.stream.Collectors;

public class GridImpl implements Grid {

    private final Map<Pair<Integer, Integer>, Cell> cells = new HashMap<>();

    private void populateBoard(int size, Set<Pair<Integer, Integer>> minePositions) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var position = new Pair<>(i, j);
                boolean isMine = minePositions.contains(position);
                var cell = new CellImpl(isMine ? CellType.MINE : CellType.EMPTY);
                this.cells.put(position, cell);
            }
        }
    }

    public GridImpl(int size, int numberOfMines) {
        Set<Pair<Integer, Integer>> minePositions = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < numberOfMines; i++) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            minePositions.add(new Pair<>(x, y));
        }
        populateBoard(size, minePositions);

    }

    public GridImpl(int size) {
        populateBoard(size, Collections.emptySet());
    }

    public GridImpl(int size, Set<Pair<Integer, Integer>> minePositions) {
        populateBoard(size, minePositions);
    }

    @Override
    public Set<Cell> getCells() {
        return new HashSet<>(this.cells.values());
    }

    @Override
    public Cell getCell(final int i, final int j) {
        if (this.cells.containsKey(new Pair<>(i, j))) {
            return this.cells.get(new Pair<>(i, j));
        } else {
            throw new IllegalArgumentException("Cell not found");
        }
    }

    @Override
    public void showCell(int i, int j) {
        if (this.cells.containsKey(new Pair<>(i, j))) {
            this.cells.get(new Pair<>(i, j)).show();
        } else {
            throw new IllegalArgumentException("Cell not found");
        }

    }

    @Override
    public boolean isMine(int i, int j) {
        return this.cells.get(new Pair<>(i, j)).isMine();
    }

    @Override
    public Set<Pair<Integer, Integer>> getAdjacent(int x, int y) {
        return this.cells.keySet().stream()
                .filter(cell -> {
                    int cellX = cell.getX();
                    int cellY = cell.getY();
                    return !(cellX == x && cellY == y) &&
                            (cellX == x - 1 || cellX == x || cellX == x + 1) &&
                            (cellY == y - 1 || cellY == y || cellY == y + 1);
                })
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<Pair<Integer, Integer>> getAdjacentMines(int x, int y) {
        return this.getAdjacent(x, y).stream()
                .filter(position -> this.isMine(position.getX(), position.getY()))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void toggleFlag(int x, int y) {
        this.cells.get(new Pair<>(x, y)).setFlag(!this.cells.get(new Pair<>(x, y)).isFlag());
    }


}
