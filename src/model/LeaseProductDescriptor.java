package model;

import java.util.LinkedList;

/**
 * LeaseProductDescriptor is a class which holds information about the lease products.
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class LeaseProductDescriptor {
    private String name;
    private String description;
    private double leasedPrice;
    private LinkedList<LeaseProduct> leaseProducts;

    public LeaseProductDescriptor(String name, String description, double leasedPrice) {
        this.name = name;
        this.description = description;
        this.leasedPrice = leasedPrice;
        this.leaseProducts = new LinkedList<>();
    }

    public void addLeaseProduct(LeaseProduct product) {
        leaseProducts.add(product);
    }

    public LinkedList<LeaseProduct> getLeaseProducts() {
        return leaseProducts;
    }

    public void setLeaseProducts(LinkedList<LeaseProduct> leaseProducts) {
        this.leaseProducts = leaseProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLeasedPrice() {
        return leasedPrice;
    }

    public void setLeasedPrice(double leasedPrice) {
        this.leasedPrice = leasedPrice;
    }
}
