package container;

import model.Sale;

import java.util.LinkedList;
/**
 * SalesContainer is a singleton container which contains a LinkedList
 * of the sales made by the company
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.Sale
 */
public class SalesContainer {
    private static SalesContainer instance;
    private static int id;
    private final LinkedList<Sale> sales;

    private SalesContainer() {
        sales = new LinkedList<>();
    }

    public static SalesContainer getInstance() {
        if (instance == null) {
            instance = new SalesContainer();
            id = 1;

        }
        return instance;
    }

    public Boolean addSale(Sale sale) {
        sale.setId(id);
        id++;
        return sales.add(sale);
    }

    public LinkedList<Sale> getSales() {
        return sales;
    }

    public Sale getSale(int id) {
        for (Sale sale : sales) {
            if (sale.getId() == id) {
                return sale;
            }
        }
        return null;
    }

    public boolean removeSale(Sale sale) {
        return this.sales.remove(sale);
    }

}
