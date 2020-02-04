package model;

/**
 * Product is a subclass of ProductType which holds information about individual products
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class Product extends ProductType {
    private int amount;

    public Product(String name, String description, double price, String barCode, int amount) {
        super(name, description, price, barCode);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String printInfo() {
        return "Product: " + name + " with price " + price +
                " barcode " + barCode + " and amount " + amount;
    }

    public void removeAmount(int amount) {
        if (this.amount > amount) {
            this.amount = 0;
        } else {
            this.amount = 0;
        }
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }
}
