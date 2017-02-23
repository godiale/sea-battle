package game;

public class SquareBoard implements IBoard {

    private int size;

    public SquareBoard(int size) {
        this.size = size;
    }

    @Override
    public int getXSize() {
        return size;
    }

    @Override
    public int getYSize() {
        return size;
    }

    @Override
    public void placeShip(int i, int j) {
        // TODO Auto-generated method stub
    }

    @Override
    public Answer checkStrike(Strike strike) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void recordStrike(Strike strike) {
        // TODO Auto-generated method stub

    }
}
