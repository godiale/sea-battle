package game;

public class ZetAutoPlayer extends AutoPlayer {

    private int curX;
    private int curY;

    @Override
    public Point nextStrike(IBoard defend_A, IBoard attack_A) {
        final Point ret = new Point(curX, curY);
        if (curX++ >= attack_A.getXSize()-1) {
            curX = 0;
            curY++;
        }
        return ret;
    }
}
