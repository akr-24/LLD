package vendingMachine.model;
import vendingMachine.enums.*;
public class Currency {
     private CurrencyType currencyType;
     private int cnt;
     private Denomination denomination;

    public Currency(CurrencyType currencyType,int cnt, Denomination denomination){
        this.currencyType = currencyType;
        this.cnt = cnt;
        this.denomination = denomination;
    }

    CurrencyType getCurrencyType(){
        return this.currencyType;
    }




}
