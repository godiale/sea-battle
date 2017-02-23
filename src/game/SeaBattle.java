package game;

public class SeaBattle {

    public static void main(String[] args) {
        GamePlay gameplay = new LogGamePlay();

        gameplay.createBoards(new SquareBoardFactory(2));
        gameplay.setShips(new ManualShipList(new Boat()));
        gameplay.setPlayer_A(new AutoPlayer());
        gameplay.setPlayer_B(new AutoPlayer());

        gameplay.run();
    }
}
