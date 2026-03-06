package vendingMachine;

import vendingMachine.machine.VendingMachine;
import vendingMachine.enums.Denomination;
import vendingMachine.model.Product;
import vendingMachine.repository.ProductRepository;
import vendingMachine.service.ChangeCalculatorService;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        ProductRepository repo = new ProductRepository();
        Product coke = new Product(new BigDecimal("25"), 5, "Coke");
        repo.save(coke);

        VendingMachine machine =
                new VendingMachine(repo, new ChangeCalculatorService());

        machine.selectProduct(coke.getId());
        machine.insertMoney(Denomination.TEN);

        machine.insertMoney(Denomination.TEN);

        machine.insertMoney(Denomination.TEN);
    }
}
