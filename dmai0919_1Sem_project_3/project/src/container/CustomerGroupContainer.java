package container;

import model.CustomerGroup;

import java.util.LinkedList;

/**
 * CustomerGroupContainer is a singleton container which contains a LinkedList
 * of the customer groups of the company
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.CustomerGroup
 */
public class CustomerGroupContainer {
    private static CustomerGroupContainer instance;
    private final LinkedList<CustomerGroup> customerGroups;

    private CustomerGroupContainer() {
        this.customerGroups = new LinkedList<>();
    }

    public static CustomerGroupContainer getInstance() {
        if (instance == null) {
            instance = new CustomerGroupContainer();

        }
        return instance;
    }

    /*
     * This method is used to add a new customer to the container.
     * @param customer	This is the customer to be added to the container
     */
    public void addCustomerGroup(CustomerGroup group) {
        customerGroups.add(group);
    }

    /*
     * This method is used to find a customer by their card's code.
     * @param cardCode	This is the number displayed as a barcode on the back of the customer's card.
     * @return Customer	This returns the customer whose cardCode matches the entered code. Returns null if the customer is not found.
     */
    public CustomerGroup findCustomerGroup(String name) {
        for (CustomerGroup group : customerGroups) {
            if (group.getName().contains(name)) {
                return group;
            }
        }
        return null;
    }
}
