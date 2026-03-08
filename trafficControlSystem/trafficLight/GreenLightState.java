package trafficControlSystem.trafficLight;

import trafficControlSystem.system.TrafficSignal;

public class GreenLightState implements TrafficLightState {

    @Override
    public void turnRed(TrafficSignal signal) {
        System.out.println("Invalid transition GREEN -> RED");
    }

    @Override
    public void turnGreen(TrafficSignal signal) {
        System.out.println("Signal already GREEN");
    }

    @Override
    public void turnYellow(TrafficSignal signal) {

        System.out.println("Signal turning YELLOW");

        signal.setState(new YellowLightState());
    }
}