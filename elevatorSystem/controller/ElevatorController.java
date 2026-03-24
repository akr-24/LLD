package elevatorSystem.controller;

import elevatorSystem.enums.Direction;
import elevatorSystem.enums.ElevatorState;
import elevatorSystem.model.Elevator;

// One controller per elevator — runs the SCAN algorithm on its own thread.
// SCAN: serve UP requests in ascending floor order, then switch to DOWN in descending order.
public class ElevatorController implements Runnable {

    private final Elevator elevator;

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        while (true) {
            if (!elevator.hasRequests()) {
                elevator.setState(ElevatorState.IDLE);
                elevator.setDirection(Direction.IDLE);
                continue;
            }

            elevator.setState(ElevatorState.MOVING);

            Integer nextFloor = elevator.nextFloor();
            if (nextFloor == null) continue;

            System.out.println(elevator + " -> moving to floor " + nextFloor);

            elevator.setCurrentFloor(nextFloor);

            System.out.println(elevator + " -> arrived at floor " + nextFloor);
            elevator.processFloor(nextFloor);
        }
    }
}
