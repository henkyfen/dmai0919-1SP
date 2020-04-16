package controller;

import container.CustomerContainer;
import container.LeaseContainer;
import container.LeaseProductContainer;
import exception.StorageException;
import model.Customer;
import model.Employee;
import model.Lease;
import model.LeaseProduct;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * LeaseController is a class which connects the LeaseContainer and the TUI
 * package with the necessary methods
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-05
 * @see model.Lease
 * @see container.LeaseContainer
 */
public class LeaseController {
    private Lease lease;

    public LeaseController() {
        this.lease = null;
    }

    /**
     * Creates a new lease for a given employees
     *
     * @param employee The employee who made the lease
     */
    public void createLease(Employee employee) {
        lease = new Lease(employee);
    }

    /**
     * Finds a customer based on their card's code
     *
     * @param cardCode This is the barcode on the back of the customer's card
     * @return This returns a number between 0-100 which represents the percentage of discount the customer gets
     * @throws StorageException if the Customer cannot be found.
     */
    public int findCustomer(String cardCode) throws StorageException {
        Customer customer = CustomerContainer.getInstance().findCustomer(cardCode);
        if (customer != null) {
            lease.setCustomer(customer);
            return customer.getCustomerGroup().getDiscount();
        } else {
            throw new StorageException("Customer not found");
        }
    }

    /**
     * Finds a product based on it's barcode
     *
     * @param leasedCode This is the barcode on the back of the customer's card
     */
    public void findLeaseProduct(String leasedCode) throws StorageException {
        LeaseProduct leaseProduct = LeaseProductContainer.getInstance().findLeaseProduct(leasedCode);
        if (leaseProduct != null) {
            lease.addLeaseProduct(leaseProduct);
            lease.setPrice(lease.getPrice() + leaseProduct.getLeaseProductDescriptor().getLeasedPrice());
        } else {
            throw new StorageException("Product not found");
        }
    }

    /**
     * Finishes a lease when the item is added to the order. Calculates the
     * final amount and saves the lease.
     *
     * @throws StorageException if the LeaseProduct cannot be added
     */
    public void leaseFinished() throws StorageException {
        if (lease.getLeaseProducts().size() < 1) {
            throw new StorageException("Lease products not added");
        }
        double totalPrice = lease.getPrice();
        double discount = lease.getCustomer().getCustomerGroup().getDiscount();
        discount /= 100;
        totalPrice -= (totalPrice * discount);

        lease.setPrice(totalPrice);
        lease.setCreated(LocalDate.now());
        lease.setLeased(true);
        lease.setReturned(false);
        LeaseContainer.getInstance().addLease(lease);
    }

    public LinkedList<Lease> getLeases() {
        return LeaseContainer.getInstance().getLeases();
    }

    public void returnLease(int id) throws StorageException {
        Lease lease = LeaseContainer.getInstance().findLeaseID(id);
        if (lease != null) {
            lease.setReturned(true);
            lease.setLeased(false);
            for (LeaseProduct product : lease.getLeaseProducts()) {
                product.setLeased(false);
            }
            LeaseContainer.getInstance().returnLease(lease);

        } else {
            throw new StorageException("Lease not found");
        }
    }

    public Lease getLease() {
        return this.lease;
    }

    public void removeLeaseProduct(String leaseCode) {
        lease.removeLeaseItem(leaseCode);
    }
}
