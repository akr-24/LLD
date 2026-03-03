package parkingLotSystem.service;
import java.util.Map;
import java.util.HashMap;
import parkingLotSystem.model.ParkingLot;
import parkingLotSystem.model.ParkingTicket;
import parkingLotSystem.model.Vehicle;

public class ParkingService {
    private final ParkingLot parkingLot;

    private final Map<String, ParkingTicket> activeTickets;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.activeTickets = new HashMap<>();
    }

    public synchronized ParkingTicket park(Vehicle vehicle) {
        ParkingTicket ticket = parkingLot.park(vehicle);
        if (ticket != null) {
            activeTickets.put(ticket.getTicketId(), ticket);
        }
        return ticket;
    }

    public synchronized void exitVehicle(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket");
        }
        ticket.getSpot().unpark();
        ticket.closeTicket();
        activeTickets.remove(ticketId);
    }


}
