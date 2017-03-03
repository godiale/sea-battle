package game;

public interface IBoard {

    public class InvalidPlacementException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public InvalidPlacementException(String message) {
            super(message);
        }
    }

    public int getXSize();
    public int getYSize();

    public void placeShip(IShip ship, int i, int j, Direction d)
                          throws InvalidPlacementException;

    public Answer makeStrike(Point p);

    public void printShips();
}
