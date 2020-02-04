package model;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * //WarehouseOrder is a class which holds information about the orders made for the warehouses of the company.
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class WarehouseOrder implements Renderable {
    private WarehouseWorker worker;
    private LocalDate date;
    private double price;
    private LinkedList<WarehouseOrderItem> orderItems;

    public WarehouseOrder(WarehouseWorker worker, LocalDate date, double price) {
        this.worker = worker;
        this.date = date;
        this.price = price;
        this.orderItems = new LinkedList<>();
    }

    public boolean addOrderItem(WarehouseOrderItem orderItem) {
        return this.orderItems.add(orderItem);
    }

    public WarehouseWorker getWorker() {
        return worker;
    }

    public void setWorker(WarehouseWorker worker) {
        this.worker = worker;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LinkedList<WarehouseOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(LinkedList<WarehouseOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    public String getTitle() {
        String title = "Date: " + date.toString();
        
        title += " by " + worker.getName();
        
        title += "\n price: " + price;
        
        for (WarehouseOrderItem warehouseOrderItem : orderItems) {
			title += "\n\r " + warehouseOrderItem.getTitle();
		}

        return title;
    }
}
