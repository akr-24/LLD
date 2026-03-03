package parkingLotSystem.model;

public class Spot {
    private final SpotType type;
    private Vehicle currentVehicle;
    private final String spotId;

    public Spot(SpotType type, String spotId, Vehicle currentVehicle) {
        this.type = type;
        this.currentVehicle = currentVehicle;
        this.spotId = spotId;
    }

    public boolean canFit(Vehicle vehicle) {
        return this.type.ordinal() >= vehicle.getSpotType().ordinal();
    }

    public boolean isAvailable() {
        return currentVehicle == null;
    }

    public void park(Vehicle vehicle) {
        this.currentVehicle = vehicle;
    }

    public void unpark() {
        this.currentVehicle = null;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public SpotType getType() {
        return type;
    }

    public String getSpotId() {
        return spotId;
    }

}
