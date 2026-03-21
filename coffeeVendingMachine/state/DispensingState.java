package coffeeVendingMachine.state;

import coffeeVendingMachine.coffee.Coffee;
import coffeeVendingMachine.machine.VendingMachine;

public class DispensingState implements State {

    private final VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectCoffee(Coffee coffee) {
        System.out.println("[Machine] Busy dispensing. Please wait.");
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("[Machine] Busy dispensing. Please wait.");
    }

    @Override
    public void dispense() {
        Coffee coffee = machine.getSelectedCoffee();
        int inserted = machine.getAmountInserted();

        // deduct ingredients from inventory
        machine.getInventory().deduct(coffee.getRecipe());

        // collect revenue equal to coffee price
        machine.addRevenue(coffee.getPrice());

        // return change if overpaid
        int change = inserted - coffee.getPrice();
        if (change > 0) {
            System.out.println("[Machine] Dispensing " + coffee.getName()
                    + "... Here is your change: ₹" + change);
        } else {
            System.out.println("[Machine] Dispensing " + coffee.getName() + "...");
        }

        // reset and go back to idle — ready for next user
        machine.resetTransaction();
        machine.setCurrentState(machine.getIdleState());
    }

    @Override
    public void cancel() {
        System.out.println("[Machine] Cannot cancel — already dispensing.");
    }
}
