package coffeeVendingMachine.coffee;
import java.util.Map;

public class Espresso extends Coffee {
    public Espresso() {
        super("Espresso", 150, Map.of("coffee", 2, "water", 50));
    }
}
