package game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SquareBoardTest {

    @Test
    public void test_1x1() {
        IBoard board = new SquareBoard(1);
        board.placeShip(new Boat(), 0, 0);
        assertEquals(Answer.FINISHED, board.makeStrike(new Point(0,0)));
    }

    @Test
    public void test_2x2_directHitBoat() {
        for (int x = 0; x <= 1; ++x) {
            for (int y = 0; y <= 1; ++y) {
                IBoard board = new SquareBoard(2);
                board.placeShip(new Boat(), x, y);
                assertEquals(Answer.FINISHED, board.makeStrike(new Point(x,y)));
            }
        }
    }

    @Test
    public void test_2x2_1xBoat() {
        IBoard board = new SquareBoard(2);
        board.placeShip(new Boat(), 1, 0);
        assertEquals(Answer.MISS,       board.makeStrike(new Point(0,0)));
        assertEquals(Answer.MISS_AGAIN, board.makeStrike(new Point(0,0)));
        assertEquals(Answer.MISS,       board.makeStrike(new Point(1,1)));
        assertEquals(Answer.MISS,       board.makeStrike(new Point(0,1)));
        assertEquals(Answer.FINISHED,   board.makeStrike(new Point(1,0)));
    }

    @Test
    public void test_2x2_4xBoat() {
        IBoard board = new SquareBoard(2);
        board.placeShip(new Boat(), 0, 0);
        board.placeShip(new Boat(), 0, 1);
        board.placeShip(new Boat(), 1, 0);
        board.placeShip(new Boat(), 1, 1);
        assertEquals(Answer.KILLED,   board.makeStrike(new Point(1,0)));
        assertEquals(Answer.KILLED,   board.makeStrike(new Point(0,0)));
        assertEquals(Answer.KILLED,   board.makeStrike(new Point(1,1)));
        assertEquals(Answer.FINISHED, board.makeStrike(new Point(0,1)));
    }

    @Test
    public void test_2x2_Launch_Boat() {
        IBoard board = new SquareBoard(2);
        board.placeShip(new Launch(), 0, 0);
        board.placeShip(new Boat(),   1, 1);
        assertEquals(Answer.HIT,       board.makeStrike(new Point(0,0)));
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(1,1)));
        assertEquals(Answer.HIT_AGAIN, board.makeStrike(new Point(0,0)));
        assertEquals(Answer.MISS,      board.makeStrike(new Point(0,1)));
        assertEquals(Answer.FINISHED,  board.makeStrike(new Point(1,0)));
    }
}
