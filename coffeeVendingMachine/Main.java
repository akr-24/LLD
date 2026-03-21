package coffeeVendingMachine;

import coffeeVendingMachine.coffee.*;
import coffeeVendingMachine.machine.VendingMachine;

public class Main {
    public static void main(String[] args) {

        VendingMachine machine = VendingMachine.getInstance();

        // stock the menu
        machine.addToMenu(new Espresso());
        machine.addToMenu(new Cappuccino());
        machine.addToMenu(new Latte());

        machine.displayMenu();

        // ── Happy path ────────────────────────────────────────────────────────
        System.out.println("=== Happy Path ===");
        machine.selectCoffee(new Espresso());   // IDLE → COFFEE_SELECTED
        machine.insertMoney(200);               // COFFEE_SELECTED → HAS_MONEY
        machine.dispense();                     // HAS_MONEY → DISPENSING → IDLE

        // ── Exact amount ─────────────────────────────────────────────────────
        System.out.println("\n=== Exact Amount ===");
        machine.selectCoffee(new Cappuccino());
        machine.insertMoney(180);
        machine.dispense();

        // ── Invalid action: dispense before selecting ─────────────────────────
        System.out.println("\n=== Invalid: dispense before selecting ===");
        machine.dispense();

        // ── Invalid action: insert money before selecting ─────────────────────
        System.out.println("\n=== Invalid: insert money before selecting ===");
        machine.insertMoney(100);

        // ── Cancel flow ───────────────────────────────────────────────────────
        System.out.println("\n=== Cancel after inserting money ===");
        machine.selectCoffee(new Latte());
        machine.insertMoney(100);
        machine.cancel();                       // refund ₹100 → back to IDLE

        System.out.println("\nTotal revenue collected: ₹" + machine.getTotalRevenue());
    }
}
