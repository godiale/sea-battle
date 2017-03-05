package game;

public abstract class GamePlay {

    protected IBoard board_A;
    protected IBoard board_B;

    protected ShipList ships;

    protected IPlayer player_A;
    protected IPlayer player_B;

    public void setShips(ShipList ships) {
        this.ships = ships;
    }

    public void setPlayer_A(IPlayer player) {
        player_A = player;
    }

    public void setBoard_A(IBoard board) {
        board_A = board;
    }

    public void setPlayer_B(IPlayer player) {
        player_B = player;
    }

    public void setBoard_B(IBoard board) {
        board_B = board;
    }

    public abstract void run();
}
