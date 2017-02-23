package game;

public interface IPlayer {

    public void placeShips(IBoard board, IShipList ships);

    public Strike nextStrike(IBoard defend_A, IBoard attack_A);
}
