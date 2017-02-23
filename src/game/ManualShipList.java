package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ManualShipList implements IShipList {

    private List<IShip> ships;

    ManualShipList(IShip... ships) {
        this.ships = new ArrayList<IShip>();
        Stream.of(ships).forEach(ship -> this.ships.add(ship));
    }

    @Override
    public List<IShip> getShips() {
        return ships;
    }
}
