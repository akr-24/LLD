package trafficControlSystem.controller;

import java.util.List;

import trafficControlSystem.emergency.EmergencyVehicleDetector;
import trafficControlSystem.model.Road;

public class TrafficSignalController {

    private final List<Road> roads;
    private final EmergencyVehicleDetector detector;

    public TrafficSignalController(List<Road> roads) {
        this.roads = roads;
        this.detector = new EmergencyVehicleDetector();
    }

    public void operateTrafficSignals() {

        while (true) {

            for (Road road : roads) {

                var signal = road.getTrafficSignal();

                try {

                    // Emergency override
                    if (detector.hasEmergencyVehicle(road)) {

                        System.out.println("Emergency vehicle detected on " + road.getName());

                        signal.turnGreen();

                        Thread.sleep(signal.getConfig().getGreenDuration() * 1000);

                        continue;
                    }

                    // Normal cycle
                    System.out.println(road.getName() + " turning GREEN");
                    signal.turnGreen();
                    Thread.sleep(signal.getConfig().getGreenDuration() * 1000);

                    System.out.println(road.getName() + " turning YELLOW");
                    signal.turnYellow();
                    Thread.sleep(signal.getConfig().getYellowDuration() * 1000);

                    System.out.println(road.getName() + " turning RED");
                    signal.turnRed();
                    Thread.sleep(signal.getConfig().getRedDuration() * 1000);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}