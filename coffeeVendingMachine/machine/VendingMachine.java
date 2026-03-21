package coffeeVendingMachine.machine;

import coffeeVendingMachine.coffee.Coffee;
import coffeeVendingMachine.inventory.Inventory;
import coffeeVendingMachine.state.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class VendingMachine {

    // ── Singleton ────────────────────────────────────────────────────────────
    private static volatile VendingMachine instance;

    public static VendingMachine getInstance() {
        if (instance == null) {
            synchronized (VendingMachine.class) {
                if (instance == null) {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }

    // ── Machine-level data (lives forever) ───────────────────────────────────
    private final List<Coffee> menu;
    private final Inventory inventory;
    private int totalRevenue;

    // ── All possible states (created once, reused) ────────────────────────────
    private final State idleState;
    private final State coffeeSelectedState;
    private final State hasMoneyState;
    private final State dispensingState;

    // ── Transaction-level data (reset after every dispense/cancel) ────────────
    private Coffee selectedCoffee;
    private int amountInserted;
    private State currentState;

    // ── Lock for thread safety ────────────────────────────────────────────────
    private final ReentrantLock lock = new ReentrantLock();

    // ── Private constructor ───────────────────────────────────────────────────
    private VendingMachine() {
        menu = new ArrayList<>();
        inventory = new Inventory();
        totalRevenue = 0;

        // create all states once — each holds a reference back to this machine
        idleState          = new IdleState(this);
        coffeeSelectedState = new CoffeeSelectedState(this);
        hasMoneyState      = new HasMoneyState(this);
        dispensingState    = new DispensingState(this);

        currentState = idleState; // machine starts idle
    }

    // ── Public API — what users interact with ─────────────────────────────────

    public void displayMenu() {
        System.out.println("\n===== MENU =====");
        for (Coffee coffee : menu) {
            System.out.println(coffee.getName() + "  ₹" + coffee.getPrice());
        }
        System.out.println("================\n");
    }

    public void selectCoffee(Coffee coffee) {
        lock.lock();
        try {
            currentState.selectCoffee(coffee);
        } finally {
            lock.unlock();
        }
    }

    public void insertMoney(int amount) {
        lock.lock();
        try {
            currentState.insertMoney(amount);
        } finally {
            lock.unlock();
        }
    }

    public void dispense() {
        lock.lock();
        try {
            currentState.dispense();
        } finally {
            lock.unlock();
        }
    }

    public void cancel() {
        lock.lock();
        try {
            currentState.cancel();
        } finally {
            lock.unlock();
        }
    }

    // ── Menu management ───────────────────────────────────────────────────────

    public void addToMenu(Coffee coffee) {
        menu.add(coffee);
    }

    // ── Methods used by State classes to read/write machine data ─────────────

    public void setCurrentState(State state)       { this.currentState = state; }
    public void setSelectedCoffee(Coffee coffee)   { this.selectedCoffee = coffee; }
    public Coffee getSelectedCoffee()              { return selectedCoffee; }
    public int getAmountInserted()                 { return amountInserted; }
    public void addAmountInserted(int amount)      { this.amountInserted += amount; }
    public Inventory getInventory()                { return inventory; }

    public void addRevenue(int amount) {
        this.totalRevenue += amount;
    }

    public void resetTransaction() {
        this.selectedCoffee = null;
        this.amountInserted = 0;
    }

    // ── State accessors — so states can trigger transitions ───────────────────

    public State getIdleState()           { return idleState; }
    public State getCoffeeSelectedState() { return coffeeSelectedState; }
    public State getHasMoneyState()       { return hasMoneyState; }
    public State getDispensingState()     { return dispensingState; }

    public int getTotalRevenue()          { return totalRevenue; }
}
