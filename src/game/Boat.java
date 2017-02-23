package game;

import java.util.ArrayList;
import java.util.List;

public class Boat implements IShip {

    @Override
    public List<Point> getCoord() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0,0));
        return points;
    }
}
