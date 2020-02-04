package container;

import model.LeaseProduct;
import model.LeaseProductDescriptor;

import java.util.LinkedList;

/**
 * LeaseProductContainer is a singleton container which contains a LinkedList
 * of the lease products of the company
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.LeaseProduct;
 */
public class LeaseProductContainer {
    private static LeaseProductContainer instance;
    private final LinkedList<LeaseProductDescriptor> products;

    private LeaseProductContainer() {
        products = new LinkedList<>();
    }

    public static LeaseProductContainer getInstance() {
        if (instance == null) {
            instance = new LeaseProductContainer();

        }
        return instance;
    }

    public void addLeaseProduct(LeaseProductDescriptor leaseProduct) {
        products.add(leaseProduct);
    }

    public LeaseProduct findLeaseProduct(String leaseCode) {
        for (LeaseProductDescriptor leaseProductDescriptor : products) {
            for (LeaseProduct leaseProduct : leaseProductDescriptor.getLeaseProducts()) {
                if (leaseProduct.getLeasedCode().equals(leaseCode)) {
                    return leaseProduct;
                }
            }
        }
        return null;
    }
}
