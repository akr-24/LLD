package pizzaOrderingSystem.entities;

import pizzaOrderingSystem.enums.Crust;
import pizzaOrderingSystem.enums.Size;

public class BasePizza implements Pizza {
    private final String name;
    private final Size size;
    private final Crust crust;

    public BasePizza(String name, Size size, Crust crust) {
        this.name = name;
        this.size = size;
        this.crust = crust;
    }

    @Override
    public String getDescription() {
        return name + " (" + size.name() + ", " + crust.name() + " Crust)";
    }

    @Override
    public double getPrice() {
        return size.getPrice() + crust.getPrice();
    }
}
