package coffeeVendingMachine.state;

import coffeeVendingMachine.coffee.Coffee;
import coffeeVendingMachine.machine.VendingMachine;

public class IdleState implements State {

    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectCoffee(Coffee coffee) {
        machine.setSelectedCoffee(coffee);
        System.out.println("[Machine] Coffee selected: " + coffee.getName()
                + " | Price: ₹" + coffee.getPrice());
        machine.setCurrentState(machine.getCoffeeSelectedState());
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("[Machine] Please select a coffee before inserting money.");
    }

    @Override
    public void dispense() {
        System.out.println("[Machine] Please select a coffee first.");
    }

    @Override
    public void cancel() {
        System.out.println("[Machine] Nothing to cancel.");
    }
}
