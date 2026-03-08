package trafficControlSystem.trafficLight;

import trafficControlSystem.system.TrafficSignal;

public class YellowLightState implements TrafficLightState {

    @Override
    public void turnRed(TrafficSignal signal) {

        System.out.println(signal.getRoad().getName() + " turning RED");

        signal.setState(new RedLightState());
    }

    @Override
    public void turnGreen(TrafficSignal signal) {
        System.out.println("Invalid transition YELLOW -> GREEN");
    }

    @Override
    public void turnYellow(TrafficSignal signal) {
        System.out.println(signal.getRoad().getName() + " already YELLOW");
    }
}