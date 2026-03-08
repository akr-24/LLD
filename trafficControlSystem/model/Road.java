package trafficControlSystem.model;

import java.util.LinkedList;
import java.util.Queue;

import trafficControlSystem.config.SignalConfig;
import trafficControlSystem.system.TrafficSignal;

public class Road {

    private final String name;
    private final String direction;
    private final Queue<Vehicle> vehicles;
    private final TrafficSignal trafficSignal;

    public Road(String name, String direction, SignalConfig config) {
        this.name = name;
        this.direction = direction;
        this.vehicles = new LinkedList<>();
        this.trafficSignal = new TrafficSignal(config);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle peekVehicle() {
        return vehicles.peek();
    }

    public TrafficSignal getTrafficSignal() {
        return trafficSignal;
    }

    public String getName() {
        return name;
    }

    public String getDirection() {
        return direction;
    }
}