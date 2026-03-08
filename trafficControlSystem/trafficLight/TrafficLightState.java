package trafficControlSystem.trafficLight;
import trafficControlSystem.system.TrafficSignal;

public interface TrafficLightState {

    void turnRed(TrafficSignal trafficSystem);
    void turnGreen(TrafficSignal trafficSystem);
    void turnYellow(TrafficSignal trafficSystem);

}
