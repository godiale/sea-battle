package game;

import java.text.MessageFormat;

public class Point {

    public int x;
    public int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return MessageFormat.format("({0},{1})", x, y);
    }
}
