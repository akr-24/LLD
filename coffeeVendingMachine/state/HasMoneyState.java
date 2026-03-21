package coffeeVendingMachine.state;

import coffeeVendingMachine.coffee.Coffee;
import coffeeVendingMachine.machine.VendingMachine;

public class HasMoneyState implements State {

    private final VendingMachine machine;

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectCoffee(Coffee coffee) {
        System.out.println("[Machine] Already have ₹" + machine.getAmountInserted()
                + " inserted. Cancel to change selection.");
    }

    @Override
    public void insertMoney(int amount) {
        // user can add more money if previous amount was insufficient
        machine.addAmountInserted(amount);
        System.out.println("[Machine] ₹" + amount + " more inserted. Total: ₹"
                + machine.getAmountInserted());
    }

    @Override
    public void dispense() {
        Coffee coffee = machine.getSelectedCoffee();
        int inserted = machine.getAmountInserted();

        if (inserted < coffee.getPrice()) {
            System.out.println("[Machine] Insufficient amount. Need ₹"
                    + (coffee.getPrice() - inserted) + " more.");
            return;
        }

        if (!machine.getInventory().isAvailable(coffee.getRecipe())) {
            System.out.println("[Machine] Sorry, ingredients unavailable for "
                    + coffee.getName() + ". Refunding ₹" + inserted);
            machine.resetTransaction();
            machine.setCurrentState(machine.getIdleState());
            return;
        }

        // transition to dispensing — machine is now busy
        machine.setCurrentState(machine.getDispensingState());
        machine.getDispensingState().dispense();
    }

    @Override
    public void cancel() {
        int refund = machine.getAmountInserted();
        machine.resetTransaction();
        machine.setCurrentState(machine.getIdleState());
        System.out.println("[Machine] Cancelled. Refunding ₹" + refund);
    }
}
