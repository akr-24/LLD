package pizzaOrderingSystem.model;

public class BillItem {
    private final String description;
    private final double price;

    public BillItem(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
