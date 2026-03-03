package parkingLotSystem.model;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.BIKE, SpotType.SMALL);
    }
}