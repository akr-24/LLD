package vendingMachine.machine;

import vendingMachine.enums.Denomination;
import vendingMachine.model.Product;
import vendingMachine.repository.ProductRepository;
import vendingMachine.service.ChangeCalculatorService;
import vendingMachine.state.*;

public class VendingMachine {

    private VendingMachineState state;

    private ProductRepository productRepository;

    private ChangeCalculatorService changeService;

    private String selectedProduct;

    private int insertedMoney;

    public VendingMachine(ProductRepository repo,
                          ChangeCalculatorService changeService){

        this.productRepository = repo;
        this.changeService = changeService;

        this.state = new IdleState();
    }

    public void setState(VendingMachineState state){
        this.state = state;
    }

    public void selectProduct(String productId){
        state.selectProduct(this, productId);
    }

    public void insertMoney(Denomination denomination){
        state.insertMoney(this, denomination);
    }

    public void dispenseProduct(){
        state.dispenseProduct(this);
    }

    public void setSelectedProduct(String productId){
        this.selectedProduct = productId;
    }

    public void addMoney(int denomination){
        insertedMoney += denomination;
    }

    public boolean hasEnoughMoney(){

        Product p = productRepository.getProductById(selectedProduct);

        return insertedMoney >= p.getPrice().intValue();
    }

    public void dispense(){

        Product p = productRepository.getProductById(selectedProduct);

        p.decreaseQuantity();

        int change = changeService.calculateChange(
                insertedMoney,
                p.getPrice().intValue()
        );

        System.out.println("Dispensing: " + p.getName());

        if(change > 0){
            System.out.println("Returning change: " + change);
        }
    }

    public void reset(){
        insertedMoney = 0;
        selectedProduct = null;
    }
}