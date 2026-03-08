package trafficControlSystem.trafficLight;

import trafficControlSystem.system.TrafficSignal;

public class RedLightState implements TrafficLightState {

    @Override
    public void turnRed(TrafficSignal signal) {
        System.out.println("Signal already RED");
    }

    @Override
    public void turnGreen(TrafficSignal signal) {

        System.out.println("Signal turning GREEN");

        signal.setState(new GreenLightState());
    }

    @Override
    public void turnYellow(TrafficSignal signal) {
        System.out.println("Invalid transition RED -> YELLOW");
    }
}