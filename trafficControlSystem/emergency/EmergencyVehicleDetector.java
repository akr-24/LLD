package trafficControlSystem.emergency;

import trafficControlSystem.model.Road;
import trafficControlSystem.model.Vehicle;

public class EmergencyVehicleDetector {

    public boolean hasEmergencyVehicle(Road road) {

        Vehicle vehicle = road.peekVehicle();

        if (vehicle == null) {
            return false;
        }

        return vehicle.getType().isEmergency();
    }
}