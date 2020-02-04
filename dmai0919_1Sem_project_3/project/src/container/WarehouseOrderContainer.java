package container;

import model.WarehouseOrder;

import java.util.LinkedList;

/**
 * WarehouseOrderContainer is a singleton container which contains a LinkedList
 * of the orders made for the warehouses of the company
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.WarehouseOrder
 */
public class WarehouseOrderContainer {
    private static WarehouseOrderContainer instance;
    private final LinkedList<WarehouseOrder> orders;

    private WarehouseOrderContainer() {
        orders = new LinkedList<>();
    }

    public static WarehouseOrderContainer getInstance() {
        if (instance == null) {
            instance = new WarehouseOrderContainer();

        }
        return instance;
    }

    public boolean addWarehouseOrder(WarehouseOrder warehouseOrder) {
        return orders.add(warehouseOrder);
    }
    
    public LinkedList<WarehouseOrder> getOrders() {
    	return this.orders;
    }
}
