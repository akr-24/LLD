package parkingLotSystem;

import java.util.ArrayList;
import java.util.List;

import parkingLotSystem.model.Car;
import parkingLotSystem.model.Level;
import parkingLotSystem.model.ParkingLot;
import parkingLotSystem.model.ParkingTicket;
import parkingLotSystem.model.Spot;
import parkingLotSystem.model.SpotType;
import parkingLotSystem.model.Vehicle;
import parkingLotSystem.service.ParkingService;
import parkingLotSystem.strategy.FirstAssignmentStrategy;

public class ParkingLotSytem {

    public static void main(String[] args) {
        // Create a simple parking lot with one level and one spot
        List<Spot> level1Spots = new ArrayList<>();
        level1Spots.add(new Spot(SpotType.MEDIUM, "L1-S1", null));

        List<Level> levels = new ArrayList<>();
        levels.add(new Level(1, level1Spots));

        ParkingLot parkingLot = ParkingLot.getInstance(levels, new FirstAssignmentStrategy());
        ParkingService parkingService = new ParkingService(parkingLot);

        Vehicle vehicle = new Car("1234567890");
        ParkingTicket ticket = parkingService.park(vehicle);

        if (ticket != null) {
            System.out.println(ticket.getTicketId());
        } else {
            System.out.println("No spot available for the vehicle.");
        }
    }
}
