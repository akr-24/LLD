package pizzaOrderingSystem;

import pizzaOrderingSystem.entities.BasePizza;
import pizzaOrderingSystem.entities.Order;
import pizzaOrderingSystem.entities.Pizza;
import pizzaOrderingSystem.enums.Crust;
import pizzaOrderingSystem.enums.Size;
import pizzaOrderingSystem.service.OrderService;
import pizzaOrderingSystem.toppings.ExtraCheese;
import pizzaOrderingSystem.toppings.nonveg.Chicken;
import pizzaOrderingSystem.toppings.nonveg.Pepperoni;
import pizzaOrderingSystem.toppings.veg.Capsicum;
import pizzaOrderingSystem.toppings.veg.Mushroom;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Pizza 1: Margherita — Large, Thin Crust, with Mushroom + Extra Cheese
        Pizza pizza1 = new BasePizza("Margherita", Size.LARGE, Crust.THIN);
        pizza1 = new Mushroom(pizza1);
        pizza1 = new ExtraCheese(pizza1);

        // Pizza 2: BBQ Chicken — Medium, Cheese Burst, with Chicken + Pepperoni
        Pizza pizza2 = new BasePizza("BBQ Chicken", Size.MEDIUM, Crust.CHEESE_BURST);
        pizza2 = new Chicken(pizza2);
        pizza2 = new Pepperoni(pizza2);

        // Pizza 3: Veggie Delight — Small, Pan Crust, with Capsicum
        Pizza pizza3 = new BasePizza("Veggie Delight", Size.SMALL, Crust.PAN);
        pizza3 = new Capsicum(pizza3);

        // Create order with all three pizzas
        Order order = new Order(Arrays.asList(pizza1, pizza2, pizza3));

        // Place order and generate bill
        OrderService orderService = new OrderService();
        orderService.placeOrder(order);
        orderService.generateBill(order);
    }
}
