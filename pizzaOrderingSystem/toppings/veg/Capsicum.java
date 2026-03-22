package pizzaOrderingSystem.toppings.veg;

import pizzaOrderingSystem.entities.Pizza;
import pizzaOrderingSystem.toppings.ToppingDecorator;

public class Capsicum extends ToppingDecorator {
    private static final double PRICE = 25;

    public Capsicum(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Capsicum";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + PRICE;
    }
}
