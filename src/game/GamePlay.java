package game;

public abstract class GamePlay {

    protected IBoard board_A;
    protected IBoard board_B;

    protected IShipList ships;

    protected IPlayer player_A;
    protected IPlayer player_B;

    public void createBoards(IBoardFactory boardFactory) {
        board_A = boardFactory.createBoard();
        board_B = boardFactory.createBoard();
    }

    public void setShips(IShipList ships) {
        this.ships = ships;
    }

    public void setPlayer_A(IPlayer player_A) {
        this.player_A = player_A;
    }

    public void setPlayer_B(IPlayer player_B) {
        this.player_B = player_B;
    }

    public abstract void run();
}
