package pizzaOrderingSystem.toppings.nonveg;

import pizzaOrderingSystem.entities.Pizza;
import pizzaOrderingSystem.toppings.ToppingDecorator;

public class Chicken extends ToppingDecorator {
    private static final double PRICE = 60;

    public Chicken(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Chicken";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + PRICE;
    }
}
