package elevatorSystem;

import elevatorSystem.model.Elevator;
import elevatorSystem.model.ElevatorRequest;
import elevatorSystem.model.User;
import elevatorSystem.strategy.NearestElevatorFindingStrategy;
import elevatorSystem.system.ElevatorSystem;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // 3 elevators, each with capacity of 5 people, starting at floor 0
        Elevator e1 = new Elevator(1, 5);
        Elevator e2 = new Elevator(2, 5);
        Elevator e3 = new Elevator(3, 5);

        ElevatorSystem system = ElevatorSystem.getInstance(
                List.of(e1, e2, e3),
                new NearestElevatorFindingStrategy()
        );

        // User A on floor 0 wants to go to floor 6
        User userA = new User("Alice");
        system.requestElevator(new ElevatorRequest(userA, 0, 6));

        // User B on floor 5 wants to go to floor 1
        User userB = new User("Bob");
        system.requestElevator(new ElevatorRequest(userB, 5, 1));

        // User C on floor 2 wants to go to floor 8
        User userC = new User("Charlie");
        system.requestElevator(new ElevatorRequest(userC, 2, 8));

        // keep main thread alive to let elevator threads run
        Thread.sleep(15000);
    }
}
