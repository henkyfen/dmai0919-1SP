package controller;

import container.CustomerContainer;
import model.Customer;

import java.util.LinkedList;

/**
 * CustomerController is a class which connects the CustomerContainer and the GUI
 * package with the necessary methods
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2020-01-27
 * @see model.Customer;
 * @see container.CustomerContainer;
 */
public class CustomerController {
    private Customer customer;

    public CustomerController() {
        this.customer = null;
    }

    public void createCustomer(String name, String phoneNumber, String email, String address, String cardCode) {
        customer = new Customer(name, phoneNumber, email, address, cardCode);
        CustomerContainer.getInstance().addCustomer(customer);
    }

    public void updateCustomer(String name, String phoneNumber, String email, String address, String cardCode) {
        customer = CustomerContainer.getInstance().findCustomer(cardCode);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
    }

    public LinkedList<Customer> getAll() {
        return CustomerContainer.getInstance().getCustomers();
    }

    public Customer searchCustomer(String cardCode) {
        return CustomerContainer.getInstance().findCustomer(cardCode);
    }

    public void deleteCustomer(Customer customer) {
        CustomerContainer.getInstance().deleteCustomer(customer);
    }
}
