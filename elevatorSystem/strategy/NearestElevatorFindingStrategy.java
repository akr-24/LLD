package elevatorSystem.strategy;

import elevatorSystem.enums.ElevatorState;
import elevatorSystem.model.Elevator;
import elevatorSystem.model.ElevatorRequest;

import java.util.List;

public class NearestElevatorFindingStrategy implements ElevatorFindingStrategy {

    @Override
    public Elevator findElevator(List<Elevator> elevators, ElevatorRequest request) {
        Elevator best = null;
        int bestDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.isFull()) continue;

            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());

            boolean isBetter = elevator.getState() == ElevatorState.IDLE
                    || elevator.getDirection() == request.getDirection();

            if (isBetter && distance < bestDistance) {
                bestDistance = distance;
                best = elevator;
            }
        }

        return best;
    }
}
