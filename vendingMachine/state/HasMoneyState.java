package vendingMachine.state;

import vendingMachine.machine.VendingMachine;
import vendingMachine.enums.Denomination;

public class HasMoneyState implements VendingMachineState {

    @Override
    public void selectProduct(VendingMachine machine, String productId) {
        System.out.println("Product already selected.");
    }

    @Override
    public void insertMoney(VendingMachine machine, Denomination denomination) {

        System.out.println(
                "Inserted " + denomination.getCurrencyValue() +
                " (" + denomination.getCurrencyType() + ")"
        );

        machine.addMoney(denomination.getCurrencyValue());

        if(machine.hasEnoughMoney()){
            machine.setState(new DispenseState());
            machine.dispenseProduct();
        }
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Insufficient money. Please insert more.");
    }
}
