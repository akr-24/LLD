package trafficControlSystem.enums;

public enum VehicleType {

    CAR(false),
    BUS(false),
    TRUCK(false),
    AMBULANCE(true),
    FIRE_TRUCK(true);

    private final boolean emergency;

    VehicleType(boolean emergency) {
        this.emergency = emergency;
    }

    public boolean isEmergency() {
        return emergency;
    }
}