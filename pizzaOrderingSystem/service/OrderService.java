package pizzaOrderingSystem.service;

import pizzaOrderingSystem.entities.Order;
import pizzaOrderingSystem.enums.OrderStatus;
import pizzaOrderingSystem.model.Bill;

public class OrderService {

    public Order placeOrder(Order order) {
        boolean transitioned = order.transitionStatus(OrderStatus.PLACED, OrderStatus.CONFIRMED);
        if (!transitioned) {
            throw new IllegalStateException("Order " + order.getOrderId() + " cannot be placed in its current state: " + order.getStatus());
        }
        System.out.println("Order placed successfully. Order ID: " + order.getOrderId());
        return order;
    }

    public Order cancelOrder(Order order) {
        boolean transitioned = order.transitionStatus(OrderStatus.PLACED, OrderStatus.CANCELLED);
        if (!transitioned) {
            throw new IllegalStateException("Order " + order.getOrderId() + " cannot be cancelled in its current state: " + order.getStatus());
        }
        System.out.println("Order " + order.getOrderId() + " has been cancelled.");
        return order;
    }

    public Bill generateBill(Order order) {
        Bill bill = new Bill(order);
        bill.printBill();
        return bill;
    }

}
