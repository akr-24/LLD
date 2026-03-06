package vendingMachine.model;

import java.math.BigDecimal;
import java.util.UUID;

import vendingMachine.exceptions.OutOfStockException;

public class Product {
    public BigDecimal price;
    public int quantity;
    public String name;
    private final String productId;

    public Product(BigDecimal price,int quantity, String name){
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.productId = UUID.randomUUID().toString();
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.productId;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void decreaseQuantity(){
        if(quantity <= 0){
            throw new OutOfStockException();
        }
        quantity--;
    }

    public void increaseQuantity(int qty){
        quantity += qty;
    }


}
