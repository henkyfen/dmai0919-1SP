package model;

/**
 * //WarehouseOrderItem is a class which holds information about the items ordered for the warehouses.
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class WarehouseOrderItem implements Renderable {
    private WarehouseOrder order;
    private ProductType product;
    private int amount;

    public WarehouseOrderItem(WarehouseOrder order, ProductType product, int amount) {
        this.order = order;
        this.product = product;
        this.amount = amount;
    }

    public WarehouseOrder getOrder() {
        return order;
    }

    public void setOrder(WarehouseOrder order) {
        this.order = order;
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

	public String getTitle() {
		return product.getTitle() + "("+amount+")";
	}
}
