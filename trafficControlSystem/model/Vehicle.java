package trafficControlSystem.model;

import trafficControlSystem.enums.VehicleType;

public class Vehicle {
    private final VehicleType type;
    private final String numberPlate;

    public Vehicle(VehicleType type, String numberPlate){
        this.type = type;
        this.numberPlate = numberPlate;
    }

    public VehicleType getType(){
        return type;
    }

    public String getNumberPlate(){
        return numberPlate;
    }
}
