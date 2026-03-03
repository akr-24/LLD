package parkingLotSystem.model;
import java.util.List;
import parkingLotSystem.strategy.SpotAssignmentStrategy;
public class ParkingLot {
    private final List<Level> levels;
    private final SpotAssignmentStrategy spotAssignmentStrategy;

    public ParkingLot(List<Level> levels, SpotAssignmentStrategy spotAssignmentStrategy) {
        this.levels = levels;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public ParkingTicket park(Vehicle vehicle) {
        Spot allocatedSpot = spotAssignmentStrategy.assignSpot(levels, vehicle);
        if(allocatedSpot != null) {
            ParkingTicket ticket = new ParkingTicket(vehicle, allocatedSpot);
            return ticket;
        }
        return null;
    }

}
