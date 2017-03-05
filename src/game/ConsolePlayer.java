package game;

import game.IBoard.InvalidPlacementException;

public class ConsolePlayer implements IPlayer {

    private String name;
    private IConsoleIO io;

    public ConsolePlayer(IConsoleIO io) {
        this.io = io;
        this.name = io.readLine("Please enter your name: ");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void placeShips(IBoard board, ShipList ships) {
        io.printLine("Please place ships");
        for (IShip ship : ships.getShips()) {
            while (true) {
                String placement = io.readLine(ship.getName() + ": ");
                String[] parts = placement.split(",");
                if (parts.length < 2) {
                    io.printLine("Invalid format: " + placement);
                    continue;
                }
                Point p;
                try {
                    p = new Point(parts[0]);
                }
                catch (IllegalArgumentException e) {
                    io.printLine("Invalid format of the point: " + parts[0]);
                    continue;
                }
                Direction d;
                try {
                    d = Direction.valueOf(parts[1]);
                }
                catch (IllegalArgumentException e) {
                    io.printLine("Invalid direction: " + parts[1]);
                    continue;
                }
                try {
                    board.placeShip(ship, p.x, p.y, d);
                }
                catch (InvalidPlacementException e) {
                    io.printLine(e.getMessage());
                    continue;
                }
                break;
            }
        }
    }

    @Override
    public Point nextStrike() {
        while (true) {
            String str = io.readLine("Your strike: ");
            try {
                return new Point(str);
            }
            catch (IllegalArgumentException e) {
                io.printLine("Invalid format of the point: " + str);
            }
        }
    }

    @Override
    public void recordMove(Point strike, Answer answer) {
        // Person has to do this manually :)
    }
}
