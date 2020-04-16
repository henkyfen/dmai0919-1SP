package controller;

import container.ProductContainer;
import container.WarehouseOrderContainer;
import exception.StorageException;
import model.*;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * WarehouseController is a class which connects the WarehouseOrderContainer and the GUI
 * package with the necessary methods
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2020-01-27
 * @see model.WarehouseOrder;
 * @see container.WarehouseOrderContainer;
 */
public class WarehouseController {
    private WarehouseOrder order;

    /**
     * Creates a new WarehouseOrder
     * @param employee the employee who creates the order
     * @param price the total price of the order
     */
    public void acceptOrder(WarehouseWorker employee, double price) {
        if (price < 0 && employee != null) {
            return;
        }
        this.order = new WarehouseOrder(employee, LocalDate.now(), price);
    }

    /**
     * Adds new items to a WarehouseOrder
     * @param barCode the barcode of the item
     * @param amount the amount of the item
     * @throws StorageException if there are no products with the given barcode
     */
    public void acceptItem(String barCode, int amount) throws StorageException {
        ProductType product = ProductContainer.getInstance().findProduct(barCode);     
        if (product == null) {
            throw new StorageException("Product not found");
        } else if(product instanceof ProductBundle) {
        	throw new StorageException("Only products can be accepted");
        }
        
        if (amount <= 0) {
            return;
        }
        
        boolean found = false;
        for (WarehouseOrderItem item : order.getOrderItems()) {
			if (item.getProduct().getBarCode().equals(barCode)) {
				found = true;
				item.setAmount(item.getAmount() + amount);
				break;
			}
		}
        if (!found) {
        	WarehouseOrderItem orderItem = new WarehouseOrderItem(order, product, amount);

            this.order.addOrderItem(orderItem);
        }
    }

    /**
     * Removes an item from an order
     * @param barCode the barcode of the item
     * @throws StorageException when there are no products in the order with the given barcode
     */
    public void removeItem(String barCode) throws StorageException {
        LinkedList<WarehouseOrderItem> orderItems = order.getOrderItems();
        for (WarehouseOrderItem orderItem : orderItems) {
            if (orderItem.getProduct().getBarCode().equals(barCode)) {
                orderItems.remove(orderItem);
                return;
            }
        }
        throw new StorageException("Product not present");
    }

    /**
     * Finishes an order
     * @return false if the order could not be added, true if it's successfully added.
     * @throws StorageException if there are no products added to the order
     */
    public boolean finishAccepting() throws StorageException {
        if (order.getOrderItems().isEmpty()) {
            throw new StorageException("Scan some products or abort acceptance");
        }

        if (!WarehouseOrderContainer.getInstance().addWarehouseOrder(order)) {
            return false;
        }

        for (WarehouseOrderItem orderItem : order.getOrderItems()) {
            int amount = orderItem.getAmount();
            Product product = (Product) orderItem.getProduct();
            product.setAmount(product.getAmount() + amount);
        }

        return true;
    }

	public WarehouseOrder getOrder() {
		return order;
	}
	
	public LinkedList<WarehouseOrder> getOrders() {
		return WarehouseOrderContainer.getInstance().getOrders();
	}
}
