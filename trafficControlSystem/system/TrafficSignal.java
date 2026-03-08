package trafficControlSystem.system;

import trafficControlSystem.config.SignalConfig;
import trafficControlSystem.trafficLight.RedLightState;
import trafficControlSystem.trafficLight.TrafficLightState;

public class TrafficSignal {

    private TrafficLightState state;
    private final SignalConfig config;

    public TrafficSignal(SignalConfig config) {

        this.config = config;
        this.state = new RedLightState();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void turnGreen() {
        state.turnGreen(this);
    }

    public void turnYellow() {
        state.turnYellow(this);
    }

    public void turnRed() {
        state.turnRed(this);
    }

    public SignalConfig getConfig() {
        return config;
    }
}