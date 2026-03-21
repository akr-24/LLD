package coffeeVendingMachine.coffee;

public class Cappuccino extends Coffee {
    public Cappuccino() {
        super("Cappuccino", 180, java.util.Map.of(
            "coffee", 2,
            "milk",   100,
            "water",  50
        ));
    }
}
