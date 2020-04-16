package model;

import java.util.LinkedList;

/**
 * ProductBundle is a subclass of ProductType which holds information about bundles
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class ProductBundle extends ProductType {
    private final LinkedList<ProductType> products;

    public ProductBundle(String name, String description, double price, String barCode) {
        super(name, description, price, barCode);
        this.products = new LinkedList<>();
    }

    /**
     * Calculates the available amount of ProductBundles. Searches for the minimum of the amounts of each Product in the bundle.
     * @return the amount of available bundles
     */
    public int getAmount() {
        int size = products.size();
        if (size == 0) {
            return 0;
        } else {
            int amount = products.get(0).getAmount();
            for (int i = 1; i < size; i++) {
                if (products.get(i).getAmount() < amount) {
                    amount = products.get(i).getAmount();
                }
            }
            return amount;
        }
    }

    public void addProduct(ProductType product) {
        products.add(product);
    }

    public String printInfo() {
        return "Product Bundle: " + name + " with price " + price +
                " barcode " + barCode + " and amount " + this.getAmount();
    }

    public void removeAmount(int amount) {
        for (ProductType productType : products) {
            productType.removeAmount(amount);
        }
    }

    public void addAmount(int amount) {
        for (ProductType productType : products) {
            productType.addAmount(amount);
        }
    }

}
