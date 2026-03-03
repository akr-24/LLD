package parkingLotSystem.strategy;
import java.util.List;
import parkingLotSystem.model.Level;
import parkingLotSystem.model.Spot;
import parkingLotSystem.model.Vehicle;

public class FirstAssignmentStrategy implements SpotAssignmentStrategy {
    @Override
    public Spot assignSpot(List<Level> levels, Vehicle vehicle) {
        for(Level level: levels) {
            Spot allocatedSpot = level.park(vehicle);
            if(allocatedSpot != null) {
                return allocatedSpot;
            }
        }
        return null;   
    }
}
