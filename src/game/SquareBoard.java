package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SquareBoard implements IBoard {

    private int size;

    private List<IShip> ships2coord; // with real board coordinates
    private int [][] coord2ships;    // index in ships2coord, -1 if no ship
    private int [][] strikesCount;   // number of strikes to the point

    public SquareBoard(int size) {
        this.size = size;
        ships2coord = new ArrayList<IShip>();
        coord2ships = new int[size][size];
        for (int[] row: coord2ships) {
            Arrays.fill(row, -1);
        }
        strikesCount = new int[size][size];
    }

    @Override
    public int getXSize() {
        return size;
    }

    @Override
    public int getYSize() {
        return size;
    }

    @Override
    public void placeShip(IShip ship, int i, int j) {

        class PlacedShip implements IShip {
            private List<Point> points;
            public PlacedShip(IShip ship, int i, int j) {
                points = new ArrayList<Point>();
                ship.getCoord().stream().forEach(p -> points.add(new Point(p.x+i, p.y+j)));
            }
            @Override
            public List<Point> getCoord() {
                return points;
            }
        }

        IShip placedShip = new PlacedShip(ship, i, j);
        final int shipIdx = ships2coord.size();
        ships2coord.add(placedShip);
        placedShip.getCoord().stream().forEach(p -> coord2ships[p.x][p.y] = shipIdx);
    }

    @Override
    public Answer checkStrike(Point p) {
        final int shipIdx = coord2ships[p.x][p.y];
        if (shipIdx < 0) {
            if (strikesCount[p.x][p.y] > 0) {
                return Answer.MISS_AGAIN;
            } else {
                return Answer.MISS;
            }
        }

        // Now we mark point "p" as hit, and estimate where we are, then return back
        strikesCount[p.x][p.y]++;
        try {
            IShip hitShip = ships2coord.get(shipIdx);
            Predicate<Point> isHit = v -> coord2ships[v.x][v.y] >= 0;
            if (hitShip.getCoord().stream().allMatch(isHit)) {
                // We killed one ship, lets check if we killed all ships
                if (ships2coord.stream().
                        flatMap(ship -> ship.getCoord().stream()).
                            allMatch(isHit)) {
                    return Answer.FINISHED;
                } else {
                    return Answer.KILLED;
                }
            }
        } finally  {
            strikesCount[p.x][p.y]--;
        }

        if (strikesCount[p.x][p.y] > 0) {
            return Answer.HIT_AGAIN;
        } else {
            return Answer.HIT;
        }
    }

    @Override
    public void recordStrike(Point p) {
        strikesCount[p.x][p.y]++;
    }
}
