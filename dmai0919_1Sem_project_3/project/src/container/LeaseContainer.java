package container;

import model.Lease;

import java.util.LinkedList;

/**
 * LeaseContainer is a singleton container which contains a LinkedList
 * of the leases made by the company
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.Lease
 */
public class LeaseContainer {
    private static LeaseContainer instance;
    private static int count;
    private final LinkedList<Lease> leases;

    private LeaseContainer() {
        leases = new LinkedList<>();
    }

    public static LeaseContainer getInstance() {
        if (instance == null) {
            instance = new LeaseContainer();
            count = 1;
        }
        return instance;
    }

    /*
     * This method is used to add a new lease to the container.
     * @param lease	This is the lease to be added to the container
     * @return boolean	This returns true if the lease is added successfully
     */
    public boolean addLease(Lease lease) {
        lease.setID(count);
        count++;
        return leases.add(lease);
    }

    public LinkedList<Lease> getLeases() {
        return leases;
    }

    public Lease findLeaseID(int id) {
        for (Lease lease : leases) {
            int findID = lease.getID();
            if (findID == id) {
                return lease;

            }
        }
        return null;
    }

    public void returnLease(Lease lease) {
        leases.remove(lease);
    }
}