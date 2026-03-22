package pizzaOrderingSystem.toppings;

import pizzaOrderingSystem.entities.Pizza;

public class ExtraCheese extends ToppingDecorator {
    private static final double PRICE = 50;

    public ExtraCheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + PRICE;
    }
}
