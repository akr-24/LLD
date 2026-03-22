package pizzaOrderingSystem.enums;

public enum Size {
    SMALL(100),
    MEDIUM(200),
    LARGE(300);
    private final int price;

    Size(int price) {
        this.price = price;
    }

    public int getPrice(){
        return this.price;
    }
}
