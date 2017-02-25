package game;

import game.IBoard.InvalidPlacementException;

public class AutoPlayer implements IPlayer {

    private int curX;
    private int curY;

    @Override
    public void placeShips(IBoard board, IShipList ships) {
        for (IShip ship : ships.getShips()) {
            try  {
                board.placeShip(ship, 0, 1);
            } catch (InvalidPlacementException e) {}
        }
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
