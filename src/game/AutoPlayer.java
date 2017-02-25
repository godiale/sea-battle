package game;

import java.util.concurrent.ThreadLocalRandom;

import game.IBoard.InvalidPlacementException;

public abstract class AutoPlayer implements IPlayer {

    protected int [][] myStrikes;
    protected int sizeX, sizeY;

    @Override
    public void placeShips(IBoard board, IShipList ships) {
        sizeX = board.getXSize();
        sizeY = board.getYSize();
        myStrikes = new int[sizeX][sizeY];

        for (IShip ship : ships.getShips()) {
            boolean placed = false;
            while (!placed) {
                try  {
                    board.placeShip(ship, ThreadLocalRandom.current().nextInt(0, sizeX),
                                          ThreadLocalRandom.current().nextInt(0, sizeY));
                    placed = true;
                } catch (InvalidPlacementException e) {
                    // That's normal for random placement
                }
            }
        }
    }

    @Override
    public void recordMove(Point strike, Answer answer) {
        myStrikes[strike.x][strike.y]++;
    }
}
