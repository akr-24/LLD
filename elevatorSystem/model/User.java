package elevatorSystem.model;

import elevatorSystem.observer.ElevatorObserver;

public class User implements ElevatorObserver {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void onElevatorArrival(int floor, int elevatorId) {
        System.out.println("  [Notification] " + name
                + " -> Elevator " + elevatorId
                + " has arrived at floor " + floor);
    }

    @Override
    public String toString() {
        return "User[" + name + "]";
    }
}
