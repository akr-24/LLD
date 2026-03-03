package parkingLotSystem.model;

public abstract class Vehicle {
    private final String licensePlate;
    private final VehicleType type; 
    private final SpotType spotType;

    public Vehicle(String licensePlate, VehicleType type, SpotType spotType) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.spotType = spotType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return type;
    }

    public SpotType getSpotType() {
        return spotType;
    }

}
