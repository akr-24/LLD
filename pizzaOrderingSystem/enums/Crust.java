package pizzaOrderingSystem.enums;

public enum Crust {
    THIN(0),
    CHEESE_BURST(50),
    PAN(100);

    private final int price;

    Crust(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
