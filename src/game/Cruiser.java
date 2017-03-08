package game;

import java.util.ArrayList;
import java.util.List;

public class Cruiser implements IShip {

    @Override
    public String getName() {
        return "Cruiser";
    }

    @Override
    public List<Point> getCoord() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0,0));
        points.add(new Point(1,0));
        points.add(new Point(2,0));
        return points;
    }
}
