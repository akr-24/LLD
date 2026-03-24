package elevatorSystem.system;

import elevatorSystem.controller.ElevatorController;
import elevatorSystem.model.Elevator;
import elevatorSystem.model.ElevatorRequest;
import elevatorSystem.strategy.ElevatorFindingStrategy;

import java.util.List;

public class ElevatorSystem {

    private static ElevatorSystem instance;

    private final List<Elevator> elevators;
    private final ElevatorFindingStrategy findingStrategy;

    private ElevatorSystem(List<Elevator> elevators, ElevatorFindingStrategy findingStrategy) {
        this.elevators = elevators;
        this.findingStrategy = findingStrategy;
        startControllers();
    }

    public static synchronized ElevatorSystem getInstance(
            List<Elevator> elevators,
            ElevatorFindingStrategy findingStrategy) {
        if (instance == null) {
            instance = new ElevatorSystem(elevators, findingStrategy);
        }
        return instance;
    }

    // each elevator gets its own controller running on its own thread
    private void startControllers() {
        for (Elevator elevator : elevators) {
            ElevatorController controller = new ElevatorController(elevator);
            new Thread(controller).start();
        }
    }

    public void requestElevator(ElevatorRequest request) {
        Elevator elevator = findingStrategy.findElevator(elevators, request);
        if (elevator == null) {
            System.out.println("No elevator available for: " + request);
            return;
        }
        elevator.addRequest(request);
        System.out.println("Dispatched " + elevator + " for " + request);
    }
}
