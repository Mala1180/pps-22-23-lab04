package u04lab.polyglot.minesweeper.grid.cell;

public interface Cell {

    boolean isMine();

    boolean isShown();

    void show();

    boolean isFlag();

    void setFlag(boolean isFlag);
}
