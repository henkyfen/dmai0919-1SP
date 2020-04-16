package model;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Lease is a class which holds information about the leases the company makes.
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class Lease implements Renderable {

    private int leaseTime;
    private double price;
    private LinkedList<LeaseProduct> leaseProducts;
    private Customer customer;
    private Employee employee;
    private boolean isLeased;
    private boolean isReturned;
    private LocalDate created;
    private int id;

    public Lease(Employee employee) {
        this.employee = employee;
        price = 0;
        leaseProducts = new LinkedList<>();
    }

    public void addLeaseProduct(LeaseProduct leaseProduct) {
        leaseProducts.add(leaseProduct);
    }

    public int getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(int leaseTime) {
        this.leaseTime = leaseTime;
    }

    public LinkedList<LeaseProduct> getLeaseProducts() {
        return leaseProducts;
    }

    public void setLeaseProducts(LinkedList<LeaseProduct> leaseProducts) {
        this.leaseProducts = leaseProducts;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean getLeased() {
        return isLeased;
    }

    public void setLeased(boolean isLeased) {
        this.isLeased = isLeased;
    }

    public String getLeasedString() {
        if (isLeased) {
            return " Yes ";
        } else return " No ";
    }

    public boolean getReturned() {
        return isReturned;
    }

    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getInfo() {
        String result = "---------\nID: " + id + "\n\tEmployee: " + employee.getName() +
                "\tCustomer: " + customer.getName() + "\tDate: " + created.toString() + "\n";
        for (LeaseProduct leaseProduct : leaseProducts) {
            result += "\t\t'" + leaseProduct.getLeaseProductDescriptor().getName();
        }
        result += "\tLease price: " + price + "\n";

        if (customer.getCustomerGroup().getDiscount() != 0) {
            result += " (including " + customer.getCustomerGroup().getDiscount() + "% discount)\n";
        }

        return result;
    }

    @Override
    public String getTitle() {
        String title = "ID: " + id;

        if (customer != null) {
            title += " to " + customer.getName();
        } else {
            title += " to Anonymous";
        }

        return title;
    }

    public void removeLeaseItem(String leasedCode) {
        for (LeaseProduct lp : leaseProducts) {
            String code = lp.getLeasedCode();
            if (code.equals(leasedCode)) {
                leaseProducts.remove(lp);
            }
        }
    }

}
