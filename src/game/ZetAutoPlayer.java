package game;

public class ZetAutoPlayer extends AutoPlayer {

    private int curX;
    private int curY;

    public ZetAutoPlayer(String name) {
        super(name);
    }

    @Override
    public Point nextStrike() {
        final Point ret = new Point(curX, curY);
        if (curX++ >= sizeX-1) {
            curX = 0;
            curY++;
        }
        return ret;
    }
}
