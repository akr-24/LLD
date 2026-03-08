package trafficControlSystem.system;

import trafficControlSystem.config.SignalConfig;
import trafficControlSystem.model.Road;
import trafficControlSystem.trafficLight.RedLightState;
import trafficControlSystem.trafficLight.TrafficLightState;

public class TrafficSignal {

    private TrafficLightState state;
    private final Road road;
    private final SignalConfig config;

    public TrafficSignal(Road road, SignalConfig config) {

        this.road = road;
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

    public Road getRoad() {
        return road;
    }

    public SignalConfig getConfig() {
        return config;
    }
}