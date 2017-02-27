package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        assertEquals("(-1,-2)", new Point(-1,-2).toString());
    }

    @Test
    public void test_Alphabetic_toString() {
        Point.setNotation(Notation.ALPHABETIC);
        assertEquals("A1",  new Point(0,0).toString());
        assertEquals("B1",  new Point(1,0).toString());
        assertEquals("A99", new Point(0,98).toString());
        assertEquals("Z1",  new Point(25,0).toString());
        assertEquals("AA1", new Point(26,0).toString());
        assertEquals("AB1", new Point(27,0).toString());
        assertEquals("(-1,-2)", new Point(-1,-2).toString());

    }

    @Test
    public void test_Numerical_Ctor() {
        Point.setNotation(Notation.NUMERICAL);
        assertEquals(new Point(1,0), new Point("(1,0)"));
        assertEquals(new Point(1,2), new Point("(01,02)"));
        try {
            new Point("(1,0");
            fail("IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void test_Alphabetic_Ctor() {
        Point.setNotation(Notation.ALPHABETIC);
        assertEquals(new Point(0,0),  new Point("A1"));
        assertEquals(new Point(1,0),  new Point("B1"));
        assertEquals(new Point(0,98), new Point("A99"));
        assertEquals(new Point(25,0), new Point("Z1"));
        assertEquals(new Point(26,0), new Point("AA1"));
        assertEquals(new Point(27,0), new Point("AB1"));
    }

    @Test
    public void test_ALphabetic_Increment() {
        Point.setNotation(Notation.ALPHABETIC);
        Point zz  = new Point("ZZ1");
        Point aaa = new Point("AAA1");
        assertEquals(new Point(zz.x+1, zz.y), aaa);
    }
}
