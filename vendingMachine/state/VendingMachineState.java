package vendingMachine.state;

import vendingMachine.machine.VendingMachine;
import vendingMachine.enums.Denomination;

public interface VendingMachineState {

    void selectProduct(VendingMachine machine, String productId);

    void insertMoney(VendingMachine machine, Denomination denomination);

    void dispenseProduct(VendingMachine machine);
}