package parkingLotSystem.model;

import java.util.UUID;

public class ParkingTicket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final Spot spot;
    private TicketStatus status;

    public ParkingTicket(Vehicle vehicle, Spot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.status = TicketStatus.ACTIVE;
    }

    public void closeTicket() {
        this.status = TicketStatus.CLOSED;
    }

    public Spot getSpot() {
        return spot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getTicketId() {
        return ticketId;
    }

    public TicketStatus getStatus() {
        return status;
    }
}