package pizzaOrderingSystem.toppings.veg;

import pizzaOrderingSystem.entities.Pizza;
import pizzaOrderingSystem.toppings.ToppingDecorator;

public class Corn extends ToppingDecorator {
    private static final double PRICE = 20;

    public Corn(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Corn";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + PRICE;
    }
}
