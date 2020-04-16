package model;

/**
 * ProductType is a superclass which holds information about both the individual
 * and bundled products the company sells.
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public abstract class ProductType implements Renderable {
    protected String name;
    protected String description;
    protected double price;
    protected String barCode;

    public ProductType(String name, String description, double price, String barCode) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTitle() {
        return this.name + " (" + this.barCode + ")";
    }

    public abstract int getAmount();

    public abstract void addAmount(int amount);

    public abstract void removeAmount(int amount);

    public abstract String printInfo();
}
