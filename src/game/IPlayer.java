package game;

public interface IPlayer {

    public String getName();
    public void placeShips(IBoard board, IShipList ships);

    public Point nextStrike();
    public void recordMove(Point strike, Answer answer);
}
