package game;

import java.util.stream.Stream;

public class AutoPlayer implements IPlayer {

    private int curX;
    private int curY;

    @Override
    public void placeShips(IBoard board, IShipList ships) {
        Stream.of(ships.getShips()).forEach(ship ->
            board.placeShip(board.getXSize()-1, board.getYSize()-1));
    }

    @Override
    public Strike nextStrike(IBoard defend_A, IBoard attack_A) {
        final Strike ret = new Strike(curX, curY);
        if (curX++ >= attack_A.getXSize()) {
            curX = 0;
            curY++;
        }
        return ret;
    }
}
