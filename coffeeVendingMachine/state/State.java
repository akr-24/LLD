package coffeeVendingMachine.state;

import coffeeVendingMachine.coffee.Coffee;

public interface State {

    void selectCoffee(Coffee coffee);  // user picks from menu

    void insertMoney(int amount);      // user puts money in

    void dispense();                   // user hits the dispense button

    void cancel();                     // user wants their money back
}
