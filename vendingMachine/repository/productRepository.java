package vendingMachine.repository;
import java.util.ArrayList;
import java.util.List;

import vendingMachine.enums.*;
import vendingMachine.model.*;


public class productRepository {
    List<Product> products = new ArrayList<>();

    void save(Product product){
        products.add(product);
    }

    List<Product> getProductList(){
        return products;
    }

    Product getProductById(String productId){
        for(Product product: products){
            if(product.getProductId() == productId){
                return product;
            }
        }

        return "Couldn't find a product by this ID"
    }
}
