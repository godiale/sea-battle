package game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Point.Notation;
import mockit.Capturing;
import mockit.Expectations;
import mockit.VerificationsInOrder;

public class ConsolePlayerTest {

    @Capturing IConsoleIO io;
    @Capturing IBoard board;

    @Test
    public void test_name() {
        new Expectations() {{ io.readLine(anyString); result = "Human"; }};
        ConsolePlayer player = new ConsolePlayer(io);
        assertEquals("Human", player.getName());
    }

    @Test
    public void test_strikesCorrectInput() {
        Point.setNotation(Notation.ALPHABETIC);
        new Expectations() {{ io.readLine(anyString); returns("", "B2"); }};
        ConsolePlayer player = new ConsolePlayer(io);
        assertEquals(new Point(1,1), player.nextStrike());
    }

    @Test
    public void test_strikesWrongInput() {
        Point.setNotation(Notation.ALPHABETIC);
        new Expectations() {{
            io.readLine(anyString); returns ("", "B", "1", "B1");
        }};
        ConsolePlayer player = new ConsolePlayer(io);
        player.nextStrike();
        new VerificationsInOrder() {{
            io.printLine("Invalid format of the point: B");
            io.printLine("Invalid format of the point: 1");
        }};
    }

    @Test
    public void test_placeCorrectInput() {
        Point.setNotation(Notation.ALPHABETIC);
        new Expectations() {{ io.readLine(anyString); returns("", "C4,VER", "B8,HOR"); }};
        ConsolePlayer player = new ConsolePlayer(io);
        player.placeShips(board, new ShipList(new Boat(), new Launch()));
        new VerificationsInOrder() {{
            board.placeShip((Boat)any,   2, 3, Direction.VER);
            board.placeShip((Launch)any, 1, 7, Direction.HOR);
        }};
    }

    @Test
    public void test_placeWrongInput() {
        Point.setNotation(Notation.ALPHABETIC);
        new Expectations() {{
            io.readLine(anyString);
            returns("", "C4|VER", "C4", "C,VER", "4,VER", "C4,V", "C4,VER");
        }};
        ConsolePlayer player = new ConsolePlayer(io);
        player.placeShips(board, new ShipList(new Boat()));
        new VerificationsInOrder() {{
            io.printLine("Please place ships");
            io.printLine("Invalid format: C4|VER");
            io.printLine("Invalid format: C4");
            io.printLine("Invalid format of the point: C");
            io.printLine("Invalid format of the point: 4");
            io.printLine("Invalid direction: V");
        }};
    }
}