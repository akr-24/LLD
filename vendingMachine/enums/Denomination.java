package vendingMachine.enums;

public enum Denomination {
    ONE(1, "COIN"),
    TWO(2, "COIN"),
    FIVE(5, "COIN"),
    TEN(10, "NOTE"),
    TWENTY(20, "NOTE"),
    FIFTY(50, "NOTE"),
    HUNDRED(100, "NOTE");

    private final int val;
    private final String currencyType;
    Denomination(int val,String currencyType ){
        this.val = val;
        this.currencyType = currencyType;
    }

    public int getCurrencyValue(){
        return this.val;
    }

    public String getCurrencyType(){
        return this.currencyType;
    }

}
