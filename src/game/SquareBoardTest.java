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
    public void test_3x3_4xBoat() {
        IBoard board = new SquareBoard(3);
        board.placeShip(new Boat(), 0, 0, HOR);
        board.placeShip(new Boat(), 0, 2, HOR);
        board.placeShip(new Boat(), 2, 0, HOR);
        board.placeShip(new Boat(), 2, 2, HOR);
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(2,0)));
        assertEquals(Answer.HIT_AGAIN, board.makeStrike(new Point(2,0)));
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(0,0)));
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(2,2)));
        assertEquals(Answer.FINISHED,  board.makeStrike(new Point(0,2)));
    }

    @Test
    public void test_3x3_Launch_Boat() {
        IBoard board = new SquareBoard(3);
        board.placeShip(new Launch(), 0, 0, HOR);
        board.placeShip(new Boat(),   2, 2, HOR);
        assertEquals(Answer.HIT,       board.makeStrike(new Point(0,0)));
        assertEquals(Answer.KILLED,    board.makeStrike(new Point(2,2)));
        assertEquals(Answer.HIT_AGAIN, board.makeStrike(new Point(0,0)));
        assertEquals(Answer.MISS,      board.makeStrike(new Point(0,1)));
        assertEquals(Answer.FINISHED,  board.makeStrike(new Point(1,0)));
        assertEquals(Answer.HIT_AGAIN, board.makeStrike(new Point(1,0)));
    }

    @Test
    public void test_3x3_Cruiser() {
        IBoard board = new SquareBoard(3);
        board.placeShip(new Cruiser(), 0, 0, HOR);
        assertEquals(Answer.HIT,      board.makeStrike(new Point(2,0)));
        assertEquals(Answer.MISS,     board.makeStrike(new Point(2,1)));
        assertEquals(Answer.HIT,      board.makeStrike(new Point(1,0)));
        assertEquals(Answer.MISS,     board.makeStrike(new Point(1,1)));
        assertEquals(Answer.FINISHED, board.makeStrike(new Point(0,0)));
    }

    @Test
    public void test_4x4_Battleship() {
        IBoard board = new SquareBoard(4);
        board.placeShip(new Battleship(), 0, 0, VER);
        assertEquals(Answer.HIT,      board.makeStrike(new Point(0,3)));
        assertEquals(Answer.MISS,     board.makeStrike(new Point(1,3)));
        assertEquals(Answer.HIT,      board.makeStrike(new Point(0,2)));
        assertEquals(Answer.MISS,     board.makeStrike(new Point(1,2)));
        assertEquals(Answer.HIT,      board.makeStrike(new Point(0,1)));
        assertEquals(Answer.MISS,     board.makeStrike(new Point(1,1)));
        assertEquals(Answer.MISS,     board.makeStrike(new Point(1,0)));
        assertEquals(Answer.FINISHED, board.makeStrike(new Point(0,0)));
    }

    @Test
    public void test_3x3_Vertical_Launches() {
        IBoard board = new SquareBoard(3);
        board.placeShip(new Launch(), 0, 0, VER);
        board.placeShip(new Launch(), 2, 0, VER);
        assertEquals(Answer.HIT,      board.makeStrike(new Point(0,0)));
        assertEquals(Answer.KILLED,   board.makeStrike(new Point(0,1)));
        assertEquals(Answer.HIT,      board.makeStrike(new Point(2,1)));
        assertEquals(Answer.FINISHED, board.makeStrike(new Point(2,0)));
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

    @Test
    public void test_ShipsClose() {
        IBoard board = new SquareBoard(3);
        board.placeShip(new Launch(), 0, 0, HOR);
        try {
            board.placeShip(new Launch(), 1, 1, VER); fail("InvalidPlacementException not thrown");
        } catch (InvalidPlacementException e) { assertTrue(e.getMessage().contains("too close")); }
        try {
            board.placeShip(new Launch(), 2, 1, VER); fail("InvalidPlacementException not thrown");
        } catch (InvalidPlacementException e) { assertTrue(e.getMessage().contains("too close")); }
    }
}
