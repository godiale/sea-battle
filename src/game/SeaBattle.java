package game;

public class SeaBattle {

    public static void main(String[] args) {
        GamePlay gameplay = new LogGamePlay();
        gameplay.createBoards(new SquareBoardFactory(5));
        gameplay.setShips(new ManualShipList(new Launch(),
                                             new Launch(),
                                             new Boat(),
                                             new Boat()));
        gameplay.setPlayer_A(new RandomAutoPlayer());
        gameplay.setPlayer_B(new ZetAutoPlayer());
        gameplay.run();
    }
}
