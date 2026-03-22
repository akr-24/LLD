package pizzaOrderingSystem.entities;

import pizzaOrderingSystem.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final List<Pizza> pizzas;
    private OrderStatus status;

    public Order(List<Pizza> pizzas) {
        this.orderId = UUID.randomUUID();
        this.pizzas = pizzas;
        this.status = OrderStatus.PLACED;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public synchronized boolean transitionStatus(OrderStatus expected, OrderStatus next) {
        if (this.status == expected) {
            this.status = next;
            return true;
        }
        return false;
    }

    public double getTotalPrice() {
        return pizzas.stream().mapToDouble(Pizza::getPrice).sum();
    }
}
