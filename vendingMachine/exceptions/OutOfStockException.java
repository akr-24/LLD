package vendingMachine.exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }

    public OutOfStockException(){
        super("Product out of stock");
    }
}
