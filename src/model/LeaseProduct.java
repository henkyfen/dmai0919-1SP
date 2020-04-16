package model;

/**
 * LeaseProduct is a class which holds information about the items
 * customers can lease from the company.
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class LeaseProduct implements Renderable {
    private String leasedCode;
    private boolean leased;
    private LeaseProductDescriptor leaseProductDescriptor;

    public LeaseProduct(String leasedCode, boolean leased, LeaseProductDescriptor leaseProductDescriptor) {
        this.leasedCode = leasedCode;
        this.leased = leased;
        this.leaseProductDescriptor = leaseProductDescriptor;
    }

    public String getLeasedCode() {
        return leasedCode;
    }

    public void setLeasedCode(String leasedCode) {
        this.leasedCode = leasedCode;
    }

    public boolean isLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }

    public LeaseProductDescriptor getLeaseProductDescriptor() {
        return leaseProductDescriptor;
    }

    public void setLeaseProductDescriptor(LeaseProductDescriptor leaseProductDescriptor) {
        this.leaseProductDescriptor = leaseProductDescriptor;
    }

    public String getTitle() {
        return leaseProductDescriptor.getName() + " ID: " + leasedCode;
    }
}
