package pizzaOrderingSystem.model;

import pizzaOrderingSystem.entities.Order;
import pizzaOrderingSystem.entities.Pizza;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Bill {
    private final UUID orderId;
    private final List<BillItem> items;
    private final double totalAmount;

    public Bill(Order order) {
        this.orderId = order.getOrderId();
        this.items = order.getPizzas().stream()
                .map(pizza -> new BillItem(pizza.getDescription(), pizza.getPrice()))
                .collect(Collectors.toList());
        this.totalAmount = order.getTotalPrice();
    }

    public void printBill() {
        System.out.println("============================================");
        System.out.println("                   BILL                     ");
        System.out.println("============================================");
        System.out.println("Order ID : " + orderId);
        System.out.println("--------------------------------------------");
        for (BillItem item : items) {
            System.out.printf("%-32s Rs. %6.2f%n", item.getDescription(), item.getPrice());
        }
        System.out.println("--------------------------------------------");
        System.out.printf("%-32s Rs. %6.2f%n", "TOTAL", totalAmount);
        System.out.println("============================================");
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<BillItem> getItems() {
        return items;
    }
}
