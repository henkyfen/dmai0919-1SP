package model;

import java.util.ArrayList;

/**
 * CustomerGroup is a class which holds information about the different groups
 * in which the customers can be. It holds information about the group's discount
 * for both sales and leases made by the members of the group
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class CustomerGroup {
    private static int maxDiscount = 20;
    private static int maxLeaseDiscount = 20;
    private final ArrayList<Customer> customers;
    private String name;
    private int discount;
    private int leaseDiscount;

    /**
     * Constructor for objects of class CustomerGroup
     */
    public CustomerGroup(String name) {
        this.name = name;
        this.discount = 0;
        this.leaseDiscount = 0;
        customers = new ArrayList<>();
    }

    public static int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int x) {
        maxDiscount = x;
    }

    public static int getMaxLeaseDiscount() {
        return maxLeaseDiscount;
    }

    public void setMaxLeaseDiscount(int x) {
        maxLeaseDiscount = x;
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        if (discount <= maxDiscount) {
            this.discount = discount;
        }
    }

    public int getLeaseDiscount() {
        return leaseDiscount;
    }

    public void setLeaseDiscount(int leaseDiscount) {
        if (leaseDiscount <= maxLeaseDiscount) {
            this.leaseDiscount = leaseDiscount;
        }
    }

}
