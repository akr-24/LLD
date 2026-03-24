package elevatorSystem.model;

import elevatorSystem.enums.Direction;
import elevatorSystem.enums.ElevatorState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator {
    private final int id;
    private final int capacity;
    private int currentFloor;
    private int currentLoad;
    private Direction direction;
    private ElevatorState state;

    // min-heap: serve lowest floor first when going UP
    private final PriorityQueue<Integer> upRequests;

    // max-heap: serve highest floor first when going DOWN
    private final PriorityQueue<Integer> downRequests;

    // full requests assigned to this elevator — source of truth for boarding/exiting
    private final List<ElevatorRequest> pendingRequests;

    public Elevator(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentFloor = 0;
        this.currentLoad = 0;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.upRequests = new PriorityQueue<>();
        this.downRequests = new PriorityQueue<>(Collections.reverseOrder());
        this.pendingRequests = new ArrayList<>();
    }

    public synchronized void addRequest(ElevatorRequest request) {
        pendingRequests.add(request);
        enqueueOrProcess(request.getSourceFloor());
        enqueue(request.getDestinationFloor());
    }

    // if elevator is already at that floor, process it now instead of queuing
    private void enqueueOrProcess(int floor) {
        if (floor == currentFloor) {
            processFloor(floor);
        } else {
            enqueue(floor);
        }
    }

    private void enqueue(int floor) {
        if (floor > currentFloor) upRequests.offer(floor);
        else downRequests.offer(floor);
    }

    // on arrival at a floor, process all requests whose source or destination matches
    public synchronized void processFloor(int floor) {
        List<ElevatorRequest> served = new ArrayList<>();
        for (ElevatorRequest request : pendingRequests) {
            if (request.getSourceFloor() == floor) {
                currentLoad++;
                System.out.println("  " + request.getUser().getName() + " boarded at floor " + floor);
            }
            if (request.getDestinationFloor() == floor) {
                currentLoad = Math.max(0, currentLoad - 1);
                System.out.println("  " + request.getUser().getName() + " exited at floor " + floor);
                served.add(request);
            }
        }
        pendingRequests.removeAll(served);
    }

    public synchronized boolean hasRequests() {
        return !upRequests.isEmpty() || !downRequests.isEmpty();
    }

    public synchronized Integer nextFloor() {
        if (direction == Direction.UP && !upRequests.isEmpty()) return upRequests.poll();
        if (direction == Direction.DOWN && !downRequests.isEmpty()) return downRequests.poll();
        if (!upRequests.isEmpty()) { direction = Direction.UP; return upRequests.poll(); }
        if (!downRequests.isEmpty()) { direction = Direction.DOWN; return downRequests.poll(); }
        return null;
    }

    public synchronized boolean isFull() {
        return currentLoad >= capacity;
    }

    public synchronized int getCurrentFloor() { return currentFloor; }
    public synchronized void setCurrentFloor(int floor) { this.currentFloor = floor; }

    public synchronized Direction getDirection() { return direction; }
    public synchronized void setDirection(Direction direction) { this.direction = direction; }

    public synchronized ElevatorState getState() { return state; }
    public synchronized void setState(ElevatorState state) { this.state = state; }

    public int getId() { return id; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return "Elevator[id=" + id + ", floor=" + currentFloor + ", " + direction + ", " + state + "]";
    }
}
