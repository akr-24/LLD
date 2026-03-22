package pizzaOrderingSystem.toppings.nonveg;

import pizzaOrderingSystem.entities.Pizza;
import pizzaOrderingSystem.toppings.ToppingDecorator;

public class Pepperoni extends ToppingDecorator {
    private static final double PRICE = 70;

    public Pepperoni(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + PRICE;
    }
}
