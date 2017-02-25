package game;

import java.util.concurrent.ThreadLocalRandom;

public class RandomAutoPlayer extends AutoPlayer {

    @Override
    public Point nextStrike() {
        while (true) {
            final int x = ThreadLocalRandom.current().nextInt(0, sizeX);
            final int y = ThreadLocalRandom.current().nextInt(0, sizeY);
            if (myStrikes[x][y] == 0) {
                return new Point(x,y);
            }
        }
    }
}
