package vendingMachine.service;

import java.util.Currency;

import vendingMachine.model.Product;

public class dispatchService {
    private Currency amount;
    private Product product;
    
    public dispatchService(Currency amount, Product product) {
        this.amount = amount;
        this.product = product;
    }

    Product requestProduct(int quantity){
        if(amount<Product.price){
            return "insufficient funds":
        }
        if(quantity>Product.getProductQuantity()){
            return "outOfStock"; 
        }
        else if(amount>Product.price){
            // change caclculator service
        }
        else{
            
            return product;
        }
    }

}
