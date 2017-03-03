package game;


import static game.Direction.HOR;
import static game.Direction.VER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import game.IBoard.InvalidPlacementException;

public class SquareBoardTest {

    @Test
    public void test_1x1() {
        IBoard board = new SquareBoard(1);
        board.placeShip(new Boat(), 0, 0, HOR);
        assertEquals(Answer.FINISHED, board.makeStrike(new Point(0,0)));
    }

    @Test
    public void test_2x2_directHitBoat() {
        for (int x = 0; x <= 1; ++x) {
            for (int y = 0; y <= 1; ++y) {
                IBoard board = new SquareBoard(2);
                board.placeShip(new Boat(), x, y, HOR);
                assertEquals(Answer.FINISHED, board.makeStrike(new Point(x,y)));
            }
        }
    }

    @Test
    public void test_2x2_1xBoat() {
        IBoard board = new SquareBoard(2);
        board.placeShip(new Boat(), 1, 0, HOR);
        assertEquals(Answer.MISS,       board.makeStrike(new Point(0,0)));
        assertEquals(Answer.MISS_AGAIN, board.makeStrike(new Point(0,0)));
        assertEquals(Answer.MISS,       board.makeStrike(new Point(1,1)));
        assertEquals(Answer.MISS,       board.makeStrike(new Point(0,1)));
        assertEquals(Answer.FINISHED,   board.makeStrike(new Point(1,0)));
    }

    @Test
    public void test_2x2_4xBoat() {
        IBoard board = new SquareBoard(2);
        board.placeShip(new Boat(), 0, 0, HOR);
        board.placeShip(new Boat(), 0, 1, HOR);
        board.placeShip(new Boat(), 1, 0, HOR);
        board.placeShip(new Boat(), 1, 1, HOR);
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(1,0)));
        assertEquals(Answer.HIT_AGAIN, board.makeStrike(new Point(1,0)));
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(0,0)));
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(1,1)));
        assertEquals(Answer.FINISHED,  board.makeStrike(new Point(0,1)));
    }

    @Test
    public void test_2x2_Launch_Boat() {
        IBoard board = new SquareBoard(2);
        board.placeShip(new Launch(), 0, 0, HOR);
        board.placeShip(new Boat(),   1, 1, HOR);
        assertEquals(Answer.HIT,       board.makeStrike(new Point(0,0)));
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(1,1)));
        assertEquals(Answer.HIT_AGAIN, board.makeStrike(new Point(0,0)));
        assertEquals(Answer.MISS,      board.makeStrike(new Point(0,1)));
        assertEquals(Answer.FINISHED,  board.makeStrike(new Point(1,0)));
        assertEquals(Answer.HIT_AGAIN, board.makeStrike(new Point(1,0)));
    }

    @Test
    public void test_2x2_Vertical_Launches() {
        IBoard board = new SquareBoard(2);
        board.placeShip(new Launch(), 0, 0, VER);
        board.placeShip(new Launch(), 1, 0, VER);
        assertEquals(Answer.HIT,      board.makeStrike(new Point(0,0)));
        assertEquals(Answer.KILLED,   board.makeStrike(new Point(0,1)));
        assertEquals(Answer.HIT,      board.makeStrike(new Point(1,1)));
        assertEquals(Answer.FINISHED, board.makeStrike(new Point(1,0)));
    }

    @Test
    public void test_InvalidPlacement() {
        IBoard board = new SquareBoard(3);
        try {
            board.placeShip(new Launch(), -1, 0, HOR); fail("InvalidPlacementException not thrown");
        } catch (InvalidPlacementException e) { assertTrue(e.getMessage().contains("outside")); }
        try {
            board.placeShip(new Launch(), 0, -1, HOR); fail("InvalidPlacementException not thrown");
        } catch (InvalidPlacementException e) { assertTrue(e.getMessage().contains("outside")); }
        try {
            board.placeShip(new Launch(), 2, 0, HOR); fail("InvalidPlacementException not thrown");
        } catch (InvalidPlacementException e) { assertTrue(e.getMessage().contains("outside")); }
        try {
            board.placeShip(new Launch(), 0, 3, HOR); fail("InvalidPlacementException not thrown");
        } catch (InvalidPlacementException e) { assertTrue(e.getMessage().contains("outside")); }
    }

    @Test
    public void test_ShipsIntersection() {
        IBoard board = new SquareBoard(2);
        board.placeShip(new Launch(), 0, 0, HOR);
        try {
            board.placeShip(new Launch(), 1, 0, VER); fail("InvalidPlacementException not thrown");
        } catch (InvalidPlacementException e) { assertTrue(e.getMessage().contains("intersection")); }
    }
}
