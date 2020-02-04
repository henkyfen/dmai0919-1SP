package container;

import model.Customer;

import java.util.LinkedList;

/**
 * CustomerContainer is a singleton container which contains a LinkedList
 * of the customers of the company
 *
 * @author dmai0919/group3
 * @version 1.0
 * @see model.Customer
 * @since 2019-12-04
 */
public class CustomerContainer {
    private static CustomerContainer instance;
    private final LinkedList<Customer> customers;

    private CustomerContainer() {
        customers = new LinkedList<>();
    }

    public static CustomerContainer getInstance() {
        if (instance == null) {
            instance = new CustomerContainer();

        }
        return instance;
    }

    /*
     * This method is used to add a new customer to the container.
     * @param customer	This is the customer to be added to the container
     */
    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
        }
    }

    /*
     * This method is used to find a customer by their card's code.
     * @param cardCode	This is the number displayed as a barcode on the back of the customer's card.
     * @return Customer	This returns the customer whose cardCode matches the entered code. Returns null if the customer is not found.
     */
    public Customer findCustomer(String cardCode) {
        for (Customer customer : customers) {
            String code = customer.getCardCode();
            if (code.equals(cardCode)) {
                return customer;
            }
        }
        return null;
    }

    public LinkedList<Customer> getCustomers() {
        return getInstance().customers;
    }

    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }
}
