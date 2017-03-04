package game;

import java.util.List;

public interface IShip extends Cloneable {

    public String getName();
    public List<Point> getCoord();
}
