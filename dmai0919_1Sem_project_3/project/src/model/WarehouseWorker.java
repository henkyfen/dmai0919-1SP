package model;

import java.util.LinkedList;

/**
 * WarehouseWorker is a subclass of Employee which holds all information about the
 * warehouse workers of the company. They have access to already existing orders
 * and stats about the current stock of products at each warehouse
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class WarehouseWorker extends Employee {
    private LinkedList<WarehouseOrder> orders;

    public WarehouseWorker(String name, String login, String password, String phoneNumber,
                           double salary) {
        super(name, login, password, phoneNumber, salary);
        this.orders = new LinkedList<>();
    }

    public boolean addOrder(WarehouseOrder order) {
        return orders.add(order);
    }

    public LinkedList<WarehouseOrder> getOrders() {
        return orders;
    }

    public void setOrders(LinkedList<WarehouseOrder> orders) {
        this.orders = orders;
    }

}
