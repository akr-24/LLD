package coffeeVendingMachine.coffee;
import java.util.Collections;
import java.util.Map;

public abstract class Coffee {
    private final Map<String, Integer> recipe;
    private final int price;
    private final String name;

    protected Coffee(String name, int price, Map<String, Integer> recipe) {
        this.name = name;
        this.price = price;
        this.recipe = Collections.unmodifiableMap(recipe);
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public Map<String, Integer> getRecipe() { return recipe; }

}
