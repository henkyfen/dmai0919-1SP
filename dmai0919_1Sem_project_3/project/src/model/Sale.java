package model;

import exception.StorageException;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Sale is a class which holds information about a sale, the employee who
 * took the order and the customer who made the purchase
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class Sale implements Renderable {
    private double totalPrice;
    private LocalDate created;
    private Boolean order;
    private Boolean orderPicked;
    private LinkedList<SaleItem> items = new LinkedList<>();
    private Employee employee;
    private Customer customer;
    private int id;

    public Sale(Employee employee, Customer customer, Boolean order) {
        this.created = LocalDate.now();
        this.order = order;
        this.employee = employee;
        this.customer = customer;
        this.totalPrice = 0;
    }

    public Sale(Employee employee) {
        this.created = LocalDate.now();
        this.employee = employee;
        this.totalPrice = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Adds a new product to the sale
     * @param product This is the ProductType to be added to the sale
     * @param amount This is the amount of the ProductType to be added to the sale
     * @return boolean This returns true if the product is added to the sale successfully
     */
    public boolean addProduct(ProductType product, int amount) throws StorageException {
        boolean found = false;
        for (SaleItem saleItem : items) {
            if (saleItem.getProduct().equals(product)) {
                int new_amount = saleItem.getAmount() + amount;
                if (product.getAmount() < new_amount) {
                    throw new StorageException("Unsufficient product amount");
                }
                saleItem.setAmount(new_amount);
                found = true;
            }
        }
        if (!found) {
            SaleItem saleItem = new SaleItem(this, product, amount);
            return this.items.add(saleItem);
        }
        return found;
    }

    /**
     * Removes a product from a sale
     * @param product the ProductType to be removed from the sale
     * @param amount the amount of ProductTypes to be removed
     * @return true if successfully removed, false if not
     */
    public boolean removeProduct(ProductType product, int amount) {
        for (SaleItem saleItem : items) {
            if (saleItem.getProduct().equals(product)) {
                if (saleItem.getAmount() > amount) {
                    saleItem.setAmount(saleItem.getAmount() - amount);
                } else {
                    return items.remove(saleItem);
                }
            }
        }
        return false;
    }

    /**
     * Prints out all the products that are already added to the sale
     */
    public void printItems() {
        for (SaleItem item : items) {
            double itemSumPrice = item.getProduct().getPrice() * item.getAmount();
            System.out.println(item.getProduct().printInfo() + " x " +
                    item.getAmount() + " = " + itemSumPrice);
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Boolean getOrder() {
        return order;
    }

    public void setOrder(Boolean order) {
        this.order = order;
    }

    public boolean getOrderPicked() {
        return orderPicked;
    }

    public void setOrderPicked(Boolean orderPicked) {
        this.orderPicked = orderPicked;
    }

    /**
     * Gets a string based on if a Sale is picked up or not
     * @return Yes if true, No if false
     */
    public String getOrderPickedString() {
        if (orderPicked) {
            return " Yes ";
        } else return " No ";

    }

    public LinkedList<SaleItem> getItems() {
        return this.items;
    }

    public void setItems(LinkedList<SaleItem> items) {
        this.items = items;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Generates a string for use in the TUI about a sale
     * @return the generated string containing every info about a sale
     */
    public String getInfo() {

        String result = "---------\nID: " + id + "\n\tEmployee: " + employee.getName() +
                "\tCustomer: " + (customer != null ? customer.getName() : "Not registered") + "\tDate: " + created.toString() + "\n";
        for (SaleItem item : items) {
            result += "\t\t'" + item.getProduct().getName() + "' " + item.getPrice() / item.getAmount() + " x "
                    + item.getAmount() + " = " + item.getPrice() + "\n";
        }
        result += "\tTotal price: " + totalPrice + "\n";
        if (customer != null) {
            if (customer.getCustomerGroup().getDiscount() != 0) {
                result += " (including " + customer.getCustomerGroup().getDiscount() + "% discount)\n";
            }
        }
        return result;
    }

    /**
     * This is an overriden method to render the title for a Sale (used in the GUI)
     * @return the generated title
     * @see model.Renderable
     */
    @Override
    public String getTitle() {
        String title = "ID: " + id;

        if (customer != null) {
            title += " to " + customer.getName();
        } else {
            title += " to Anonymous";
        }

        title += "\n price: " + totalPrice;

        return title;
    }

}
