package parkingLotSystem.strategy;
import java.util.List;
import parkingLotSystem.model.Spot;
import parkingLotSystem.model.Vehicle;
import parkingLotSystem.model.Level;
public interface SpotAssignmentStrategy {
    Spot assignSpot(List<Level> levels, Vehicle vehicle);
}
