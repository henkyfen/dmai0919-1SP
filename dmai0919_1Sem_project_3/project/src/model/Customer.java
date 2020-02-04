package model;

import java.util.LinkedList;

/**
 * Customer is a class which holds information about a customer, their group,
 * all sales and leases made by the customer.
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class Customer implements Renderable {
    private final LinkedList<Lease> leases;
    private final LinkedList<Sale> sales;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String cardCode;
    private CustomerGroup customerGroup;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String name, String phoneNumber, String email, String address, String cardCode) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.cardCode = cardCode;
        leases = new LinkedList<>();
        sales = new LinkedList<>();
    }

    public boolean addLease(Lease l) {
        return leases.add(l);
    }

    public boolean addSale(Sale s) {
        return sales.add(s);
    }

    public LinkedList<Sale> getSales() {
        return sales;
    }

    public LinkedList<Lease> getLeases() {
        return leases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public CustomerGroup getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(CustomerGroup customerGroup) {
        this.customerGroup = customerGroup;
    }

    @Override
    public String getTitle() {
        return (name + " (" + cardCode + ")");
    }

}
