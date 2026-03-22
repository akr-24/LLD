package pizzaOrderingSystem.toppings;

import pizzaOrderingSystem.entities.Pizza;

public abstract class ToppingDecorator implements Pizza {
    protected final Pizza pizza;

    protected ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }
}
