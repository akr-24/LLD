package elevatorSystem.strategy;

import elevatorSystem.model.Elevator;
import elevatorSystem.model.ElevatorRequest;

import java.util.List;

public interface ElevatorFindingStrategy {
    Elevator findElevator(List<Elevator> elevators, ElevatorRequest request);
}
