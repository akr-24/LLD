package coffeeVendingMachine.inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private static final int LOW_STOCK_THRESHOLD = 5;

    // ingredient name -> quantity available
    private final Map<String, Integer> stock;

    public Inventory() {
        stock = new HashMap<>();
        initializeStock();
    }

    private void initializeStock() {
        stock.put("coffee", 100);
        stock.put("milk",   200);
        stock.put("water",  500);
        stock.put("sugar",  100);
    }

    // Checks if all ingredients in the recipe are available in required quantities
    public synchronized boolean isAvailable(Map<String, Integer> recipe) {
        for (Map.Entry<String, Integer> entry : recipe.entrySet()) {
            String ingredient = entry.getKey();
            int required = entry.getValue();
            int available = stock.getOrDefault(ingredient, 0);
            if (available < required) return false;
        }
        return true;
    }

    // Deducts ingredients — call only after isAvailable check, within same lock
    public synchronized void deduct(Map<String, Integer> recipe) {
        for (Map.Entry<String, Integer> entry : recipe.entrySet()) {
            String ingredient = entry.getKey();
            int required = entry.getValue();
            stock.put(ingredient, stock.get(ingredient) - required);
        }
        notifyIfLowStock(recipe);
    }

    public synchronized void restock(String ingredient, int quantity) {
        stock.put(ingredient, stock.getOrDefault(ingredient, 0) + quantity);
        System.out.println("[Inventory] Restocked " + ingredient + " by " + quantity + " units.");
    }

    private void notifyIfLowStock(Map<String, Integer> recipe) {
        for (String ingredient : recipe.keySet()) {
            if (stock.getOrDefault(ingredient, 0) < LOW_STOCK_THRESHOLD) {
                System.out.println("[ALERT] Low stock: " + ingredient
                        + " has only " + stock.get(ingredient) + " units left.");
            }
        }
    }
}
