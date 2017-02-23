package game;

import java.text.MessageFormat;

public class Strike {

    public int x;
    public int y;

    Strike(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return MessageFormat.format(">({0},{1})", x, y);
    }
}
