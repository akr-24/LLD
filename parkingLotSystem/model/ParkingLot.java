package parkingLotSystem.model;
import java.util.List;
import parkingLotSystem.strategy.SpotAssignmentStrategy;
public class ParkingLot {
    private static ParkingLot instance;

    private final List<Level> levels;
    private final SpotAssignmentStrategy spotAssignmentStrategy;

    private ParkingLot(List<Level> levels, SpotAssignmentStrategy spotAssignmentStrategy) {
        this.levels = levels;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public static synchronized ParkingLot getInstance(List<Level> levels, SpotAssignmentStrategy spotAssignmentStrategy) {
        if (instance == null) {
            instance = new ParkingLot(levels, spotAssignmentStrategy);
        }
        return instance;
    }

    public synchronized ParkingTicket park(Vehicle vehicle) {
        Spot allocatedSpot = spotAssignmentStrategy.assignSpot(levels, vehicle);
        if(allocatedSpot != null) {
            ParkingTicket ticket = new ParkingTicket(vehicle, allocatedSpot);
            return ticket;
        }
        return null;
    }

}
