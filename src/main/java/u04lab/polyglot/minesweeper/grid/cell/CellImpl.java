package u04lab.polyglot.minesweeper.grid.cell;

public class CellImpl implements Cell {

    private boolean isShown;
    private final CellType type;
    private boolean isFlag;

    public CellImpl(final CellType cellType) {
        this.type = cellType;
        this.isShown = false;
        this.isFlag = false;
    }

    @Override
    public boolean isMine() {
        return this.type == CellType.MINE;
    }

    @Override
    public boolean isShown() {
        return isShown;
    }

    @Override
    public void show() {
        this.isShown = true;
    }

    @Override
    public boolean isFlag() {
        return this.isFlag;
    }

    @Override
    public void setFlag(boolean isFlag) {
        this.isFlag = isFlag;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "isShown=" + isShown +
                ", type=" + type +
                ", isFlag='" + isFlag + '\'' +
                '}';
    }
}
