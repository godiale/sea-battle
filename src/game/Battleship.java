package game;

import java.util.ArrayList;
import java.util.List;

public class Battleship implements IShip {

    @Override
    public String getName() {
        return "Battleship";
    }

    @Override
    public List<Point> getCoord() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0,0));
        points.add(new Point(1,0));
        points.add(new Point(2,0));
        points.add(new Point(3,0));
        return points;
    }
}
