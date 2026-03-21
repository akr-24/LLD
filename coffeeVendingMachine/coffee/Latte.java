package coffeeVendingMachine.coffee;

public class Latte extends Coffee {
    public Latte() {
        super("Latte", 200, java.util.Map.of(
            "coffee", 1,
            "milk",   150,
            "water",  30,
            "sugar",  2
        ));
    }
}
