package trafficControlSystem;

import java.util.Arrays;

import trafficControlSystem.config.SignalConfig;
import trafficControlSystem.controller.TrafficSignalController;
import trafficControlSystem.enums.VehicleType;
import trafficControlSystem.model.Road;
import trafficControlSystem.model.Vehicle;

public class Main {

    public static void main(String[] args) {

        SignalConfig config = new SignalConfig(5, 7, 3);

        Road northRoad = new Road("North Road", "NORTH", config);
        Road southRoad = new Road("South Road", "SOUTH", config);
        Road eastRoad = new Road("East Road", "EAST", config);
        Road westRoad = new Road("West Road", "WEST", config);

        northRoad.addVehicle(new Vehicle(VehicleType.CAR, "UP32AB1234"));
        eastRoad.addVehicle(new Vehicle(VehicleType.AMBULANCE, "UP32EM9999"));

        TrafficSignalController controller =
                new TrafficSignalController(
                        Arrays.asList(
                                northRoad,
                                southRoad,
                                eastRoad,
                                westRoad
                        )
                );

        controller.operateTrafficSignals();
    }
}