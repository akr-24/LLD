package trafficControlSystem.trafficLight;

import trafficControlSystem.system.TrafficSignal;

public class GreenLightState implements TrafficLightState {

    @Override
    public void turnRed(TrafficSignal signal) {
        System.out.println("Invalid transition GREEN -> RED");
    }

    @Override
    public void turnGreen(TrafficSignal signal) {
        System.out.println(signal.getRoad().getName() + " already GREEN");
    }

    @Override
    public void turnYellow(TrafficSignal signal) {

        System.out.println(signal.getRoad().getName() + " turning YELLOW");

        signal.setState(new YellowLightState());
    }
}