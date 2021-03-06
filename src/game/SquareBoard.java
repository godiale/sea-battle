package game;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class SquareBoard implements IBoard {

    class PlacedShip implements IShip {

        IShip originalShip;
        private List<Point> points;

        public PlacedShip(IShip ship, int i, int j, Direction d) {
            originalShip = ship;
            points = new ArrayList<Point>();
            ship.getCoord().stream().forEach(
                    p -> points.add(
                            d == Direction.HOR
                                ? new Point(p.x+i, p.y+j)
                                : new Point(p.y+i, p.x+j)));
        }
        @Override
        public List<Point> getCoord() {
            return points;
        }
        public IShip getOriginalShip() {
            return originalShip;
        }
        @Override
        public String getName() {
            return null;
        }
    }

    private int size;

    private List<PlacedShip> ships2coord; // with real board coordinates
    private int [][] coord2ships;    // index in ships2coord, -1 if no ship
    private int [][] strikesCount;   // number of strikes to the point

    public SquareBoard(int size) {
        this.size = size;
        ships2coord = new ArrayList<PlacedShip>();
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
    public void placeShip(IShip ship, int i, int j, Direction d)
                                            throws InvalidPlacementException {
        PlacedShip placedShip = new PlacedShip(ship, i, j, d);

        Predicate<Point> isOutBoard = v -> v.x < 0 || v.x >= size ||
                                           v.y < 0 || v.y >= size;
        if (placedShip.getCoord().stream().anyMatch(isOutBoard)) {
            throw new InvalidPlacementException("Ship is outside of the board");
        }

        Predicate<Point> isIntersection = v -> coord2ships[v.x][v.y] >= 0;
        if (placedShip.getCoord().stream().anyMatch(isIntersection)) {
            throw new InvalidPlacementException("Ships intersection");
        }

        List<Point> halo = new ArrayList<Point>();
        IntStream.rangeClosed(-1, 1).forEach(
                x -> IntStream.rangeClosed(-1, 1).forEach(
                        y -> halo.add(new Point(x,y))));
        Predicate<Point> isHaloIntesection = v -> halo.stream().anyMatch(
                h -> isOutBoard.negate().and(isIntersection).test(
                        new Point(v.x+h.x, v.y+h.y)));
        if (placedShip.getCoord().stream().anyMatch(isHaloIntesection)) {
            throw new InvalidPlacementException("Ships too close");
        }

        final int shipIdx = ships2coord.size();
        ships2coord.add(placedShip);
        placedShip.getCoord().stream().forEach(p -> coord2ships[p.x][p.y] = shipIdx);
    }

    @Override
    public Answer makeStrike(Point p) {
        strikesCount[p.x][p.y]++;

        final int shipIdx = coord2ships[p.x][p.y];
        if (shipIdx < 0) {
            if (strikesCount[p.x][p.y] > 1) {
                return Answer.MISS_AGAIN;
            } else {
                return Answer.MISS;
            }
        }

        if (strikesCount[p.x][p.y] > 1) {
            return Answer.HIT_AGAIN;
        }

        IShip hitShip = ships2coord.get(shipIdx);
        Predicate<Point> isHit = v -> strikesCount[v.x][v.y] > 0;
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

        return Answer.HIT;
    }

    @Override
    public void printShips() {
        for (PlacedShip ship : ships2coord) {
            StringWriter str = new StringWriter();
            str.write(ship.getOriginalShip().getClass().getSimpleName() + ": ");
            for (Point p : ship.getCoord()) {
                str.write(p.toString() + " ");
            }
            System.out.println(str.toString());
        }
    }
}
