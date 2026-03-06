package vendingMachine.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingMachine.exceptions.ProductNotFoundException;

import vendingMachine.model.*;


public class ProductRepository {
    private Map<String, Product> products = new HashMap<>();

    public void save(Product product){
        products.put(product.getId(), product);
    }

    public List<Product>getProductList(){
       return new ArrayList<>(products.values());
    }

    public Product getProductById(String productId){ 
        Product p = products.get(productId);
        
        if(p==null){
            throw new ProductNotFoundException();
        }

        return p;
    }
}
