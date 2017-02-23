package game;

public class AutoPlayer implements IPlayer {

    private int curX;
    private int curY;

    @Override
    public void placeShips(IBoard board, IShipList ships) {
        ships.getShips().stream().forEach(ship ->
            board.placeShip(ship, board.getXSize()-1, board.getYSize()-1));
    }

    @Override
    public Point nextStrike(IBoard defend_A, IBoard attack_A) {
        final Point ret = new Point(curX, curY);
        if (curX++ >= attack_A.getXSize()-1) {
            curX = 0;
            curY++;
        }
        return ret;
    }
}
