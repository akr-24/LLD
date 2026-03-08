package trafficControlSystem.config;

public class SignalConfig {
    private int redLightDuration;
    private int greenLightDuration;
    private int yellowLightDuration;

    public SignalConfig(int redLightDuration, int greenLightDuration, int yellowLightDuration){
        this.greenLightDuration = greenLightDuration;
        this.redLightDuration = redLightDuration;
        this.yellowLightDuration = yellowLightDuration;
    }

    public int getRedDuration(){
        return redLightDuration;
    }

    public int getGreenDuration(){
        return greenLightDuration;
    }

    public int getYellowDuration(){
        return yellowLightDuration;
    }
}
