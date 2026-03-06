package vendingMachine.state;

import vendingMachine.machine.VendingMachine;
import vendingMachine.enums.Denomination;

public class DispenseState implements VendingMachineState {

    @Override
    public void selectProduct(VendingMachine machine, String productId) {
        System.out.println("Processing transaction. Please wait.");
    }

    @Override
    public void insertMoney(VendingMachine machine, Denomination denomination) {
        System.out.println("Cannot insert money while dispensing.");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {

        machine.dispense();

        machine.reset();

        machine.setState(new IdleState());
    }
}
