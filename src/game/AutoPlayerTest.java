package game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AutoPlayerTest {

    @Test
    public void test_name() {
        assertEquals("A", new ZetAutoPlayer("A").getName());
        assertEquals("B", new ZetAutoPlayer("B").getName());
    }
}
