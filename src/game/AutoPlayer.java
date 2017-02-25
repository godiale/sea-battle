package game;

import java.util.concurrent.ThreadLocalRandom;

import game.IBoard.InvalidPlacementException;

public abstract class AutoPlayer implements IPlayer {

    @Override
    public void placeShips(IBoard board, IShipList ships) {
        for (IShip ship : ships.getShips()) {
            boolean placed = false;
            while (!placed) {
                try  {
                    board.placeShip(ship, ThreadLocalRandom.current().nextInt(0, board.getXSize()),
                                          ThreadLocalRandom.current().nextInt(0, board.getYSize()));

                    placed = true;
                } catch (InvalidPlacementException e) {
                    // That's normal for random placement
                }
            }
        }
    }
}
