package game;

import java.io.IOException;
import java.util.stream.IntStream;

import game.Point.Notation;

public class SeaBattle {

    public static void main(String[] args) {
        Point.setNotation(Notation.ALPHABETIC);
        IBoardFactory boardFactory = new SquareBoardFactory(10);

        GamePlay gameplay = new LogGamePlay();

        gameplay.setBoard_A(boardFactory.createBoard());
        gameplay.setBoard_B(boardFactory.createBoard());
        ShipList shipList = new ShipList();
        IntStream.rangeClosed(0,0).forEach(v -> shipList.add(new Battleship()));
        IntStream.rangeClosed(0,1).forEach(v -> shipList.add(new Cruiser()));
        IntStream.rangeClosed(0,2).forEach(v -> shipList.add(new Launch()));
        IntStream.rangeClosed(0,3).forEach(v -> shipList.add(new Boat()));
        gameplay.setShips(shipList);
        gameplay.setPlayer_A(new ConsolePlayer(new SystemConsoleIO()));
        gameplay.setPlayer_B(new RandomAutoPlayer("Random"));

        gameplay.run();

        // do not close console until user sees last message
        try { System.in.read(); } catch (IOException e) {}
    }
}
