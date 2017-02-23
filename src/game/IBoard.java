package game;

public interface IBoard {

    public int getXSize();
    public int getYSize();

    public void placeShip(int i, int j);
    public Answer checkStrike(Strike strike);
    public void recordStrike(Strike strike);
}
