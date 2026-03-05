package vendingMachine.model;

import java.util.UUID;

public class Product {
    public Decimal price;
    public int quantity;
    public String name;
    private final String productId;

    public Product(Decimal price,int quantity, String name){
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.productId = UUID.randomUUID().toString();
    }

    String getProductId(){
        return this.productId;
    }

    int getProductQuantity(){
        return this.quantity;
    }

    Decimal getProductPrice() {
        return this.price;
    }

    void decreaseQuantity(){
        if(this.quantity>0)
          this.quantity -= 1;
    }


}
