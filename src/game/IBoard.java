package game;

public interface IBoard {

    public int getXSize();
    public int getYSize();

    public void placeShip(IShip ship, int i, int j);
    public Answer makeStrike(Point p);
}
