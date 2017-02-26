package game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Point.Notation;

public class PointTest {

    @Test
    public void test_Numerical_toString() {
        Point.setNotation(Notation.NUMERICAL);
        assertEquals("(0,0)",  new Point(0,0).toString());
        assertEquals("(0,98)", new Point(0,98).toString());
        assertEquals("(25,0)", new Point(25,0).toString());
        assertEquals("(26,0)", new Point(26,0).toString());
        // TODO: assertEquals("(-1,0)", new Point(26,0).toString());
        // TODO: negative
    }

    @Test
    public void test_Alphabetic_toString() {
        Point.setNotation(Notation.ALPHABETIC);
        assertEquals("A1",  new Point(0,0).toString());
        assertEquals("A99", new Point(0,98).toString());
        assertEquals("Z1",  new Point(25,0).toString());
        assertEquals("AA1", new Point(26,0).toString());
        // TODO: negative
        // TODO: boundary --> ZZ + 1 --> AAA
    }

    @Test
    public void test_Numerical_Ctor() {
        Point.setNotation(Notation.NUMERICAL);
        assertEquals(new Point(1,0), new Point("(1,0)"));
        // TODO: invalid throws exception
        // TODO: check trim leading zeroes
    }
}
