package game;

public class SeaBattle {

    public static void main(String[] args) {
        GamePlay gameplay = new LogGamePlay();
        IBoardFactory boardFactory = new SquareBoardFactory(5);
        gameplay.setBoard_A(boardFactory.createBoard());
        gameplay.setBoard_B(boardFactory.createBoard());
        gameplay.setShips(new ManualShipList(new Launch(),
                                             new Launch(),
                                             new Boat(),
                                             new Boat()));
        gameplay.setPlayer_A(new RandomAutoPlayer());
        gameplay.setPlayer_B(new ZetAutoPlayer());
        gameplay.run();
    }
}
