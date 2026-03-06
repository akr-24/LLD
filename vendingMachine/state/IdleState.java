package vendingMachine.state;

import vendingMachine.machine.VendingMachine;
import vendingMachine.enums.Denomination;

public class IdleState implements VendingMachineState {

    @Override
    public void selectProduct(VendingMachine machine, String productId) {

        machine.setSelectedProduct(productId);

        machine.setState(new HasMoneyState());

        System.out.println("Product selected. Please insert money.");
    }

    @Override
    public void insertMoney(VendingMachine machine, Denomination denomination) {
        System.out.println("Please select product first.");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please select product first.");
    }
}