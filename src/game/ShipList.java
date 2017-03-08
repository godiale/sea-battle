package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ShipList {

    private List<IShip> ships;

    ShipList(IShip... ships) {
        this.ships = new ArrayList<IShip>();
        Stream.of(ships).forEach(ship -> this.ships.add(ship));
    }

    public void add(IShip ship) {
        ships.add(ship);
    }

    public List<IShip> getShips() {
        return ships;
    }
}
