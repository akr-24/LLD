package parkingLotSystem.model;
import java.util.List;

public class Level {
    private final List<Spot> spots;
    private final int levelNumber;

    public Level(int levelNumber, List<Spot> spots) {
        this.levelNumber = levelNumber;
        this.spots = spots;
    }

    public Spot park(Vehicle vehicle) {
        for(Spot spot: spots) {
            if(spot.canFit(vehicle)) {
                spot.park(vehicle);
                return spot;
            }
        }
        return null;
    }

    public boolean unpark(Vehicle vehicle) {
        for(Spot spot: spots) {
            if(spot.getCurrentVehicle() == vehicle) {
                spot.unpark();
                return true;
            }
        }
        return false;
    }
    
    public int getLevelNumber() {
        return levelNumber;
    }

}
