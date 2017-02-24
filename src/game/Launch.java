package game;

import java.util.ArrayList;
import java.util.List;

public class Launch implements IShip {

    @Override
    public List<Point> getCoord() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0,0));
        points.add(new Point(1,0));
        return points;
    }
}
