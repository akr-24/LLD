package vendingMachine.service;

public class ChangeCalculatorService {

    public int calculateChange(int insertedMoney, int price){
        return insertedMoney - price;
    }
}
