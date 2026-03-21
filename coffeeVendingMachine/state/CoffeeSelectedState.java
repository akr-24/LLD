package coffeeVendingMachine.state;

import coffeeVendingMachine.coffee.Coffee;
import coffeeVendingMachine.machine.VendingMachine;

public class CoffeeSelectedState implements State {

    private final VendingMachine machine;

    public CoffeeSelectedState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectCoffee(Coffee coffee) {
        // allow changing selection before money is inserted
        machine.setSelectedCoffee(coffee);
        System.out.println("[Machine] Selection changed to: " + coffee.getName()
                + " | Price: ₹" + coffee.getPrice());
    }

    @Override
    public void insertMoney(int amount) {
        machine.addAmountInserted(amount);
        System.out.println("[Machine] ₹" + amount + " inserted. Total: ₹"
                + machine.getAmountInserted());
        machine.setCurrentState(machine.getHasMoneyState());
    }

    @Override
    public void dispense() {
        System.out.println("[Machine] Please insert money first. Price: ₹"
                + machine.getSelectedCoffee().getPrice());
    }

    @Override
    public void cancel() {
        machine.setSelectedCoffee(null);
        machine.setCurrentState(machine.getIdleState());
        System.out.println("[Machine] Selection cancelled. Returning to idle.");
    }
}
