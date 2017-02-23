package game;

public class SquareBoardFactory implements IBoardFactory {

    private int size;

    public SquareBoardFactory(int size) {
        this.size = size;
    }

    @Override
    public IBoard createBoard() {
        return new SquareBoard(size);
    }
}
