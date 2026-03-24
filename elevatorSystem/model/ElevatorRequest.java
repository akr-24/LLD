package elevatorSystem.model;

import elevatorSystem.enums.Direction;

public class ElevatorRequest {
    private final User user;
    private final int sourceFloor;
    private final int destinationFloor;
    private final Direction direction;

    public ElevatorRequest(User user, int sourceFloor, int destinationFloor) {
        if (sourceFloor == destinationFloor) {
            throw new IllegalArgumentException("Source and destination floor cannot be the same.");
        }
        this.user = user;
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.direction = sourceFloor < destinationFloor ? Direction.UP : Direction.DOWN;
    }

    public User getUser() {
        return user;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Request[" + user.getName() + " : floor " + sourceFloor + " -> floor " + destinationFloor + " (" + direction + ")]";
    }
}
