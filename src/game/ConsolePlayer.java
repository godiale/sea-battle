package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import game.IBoard.InvalidPlacementException;

public class ConsolePlayer implements IPlayer {

    private String name;

    private String readLine(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                return bufferedReader.readLine();
            }
            catch (IOException e) {
                System.out.println("Error occured (will retry): " + e);
            }
        }
    }

    public ConsolePlayer() {
        name = readLine("Please enter your name: ");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void placeShips(IBoard board, IShipList ships) {
        System.out.println("Please place ships");
        for (IShip ship : ships.getShips()) {
            while (true) {
                String placement = readLine(ship.getName() + ": ");
                String[] parts = placement.split(",");
                if (parts.length < 2) {
                    System.out.println("Please enter point and direction");
                    continue;
                }
                Point p;
                try {
                    p = new Point(parts[0]);
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                Direction d;
                try {
                    d = Direction.valueOf(parts[1]);
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                try {
                    board.placeShip(ship, p.x, p.y, d);
                }
                catch (InvalidPlacementException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                break;
            }
        }
    }

    @Override
    public Point nextStrike() {
        while (true) {
            String str = readLine("Your strike: ");
            try {
                return new Point(str);
            }
            catch (InvalidPlacementException e) {
                System.out.println(e);
                continue;
            }
        }
    }

    @Override
    public void recordMove(Point strike, Answer answer) {
    }

}
