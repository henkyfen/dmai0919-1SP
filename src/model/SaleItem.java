package model;

/**
 * //SaleItem is a class which holds information about both ProductTypes (individual product and product bundle)
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class SaleItem implements Renderable {
    private Sale sale;
    private ProductType product;
    private int amount;

    public SaleItem(Sale sale, ProductType product, int amount) {
        super();
        this.sale = sale;
        this.product = product;
        this.amount = amount;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public ProductType getProduct() {
        return product;
    }

    public void setProduct(ProductType product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return product.getPrice() * amount;
    }

    public String getTitle() {
        return product.getTitle() + " : " + amount;
    }

}
