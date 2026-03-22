package pizzaOrderingSystem.toppings.veg;

import pizzaOrderingSystem.entities.Pizza;
import pizzaOrderingSystem.toppings.ToppingDecorator;

public class Mushroom extends ToppingDecorator {
    private static final double PRICE = 30;

    public Mushroom(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushroom";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + PRICE;
    }
}
