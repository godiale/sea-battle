package game;

import game.Point.Notation;

public class SeaBattle {

    public static void main(String[] args) {
        Point.setNotation(Notation.NUMERICAL);
        IBoardFactory boardFactory = new SquareBoardFactory(5);

        GamePlay gameplay = new LogGamePlay();

        gameplay.setBoard_A(boardFactory.createBoard());
        gameplay.setBoard_B(boardFactory.createBoard());
        gameplay.setShips(new ManualShipList(new Launch(),
                                             new Launch(),
                                             new Boat(),
                                             new Boat()));
        gameplay.setPlayer_A(new RandomAutoPlayer("Rand"));
        gameplay.setPlayer_B(new ZetAutoPlayer("Zeta"));

        gameplay.run();
    }
}
