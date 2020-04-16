package controller;

import container.CustomerContainer;
import container.ProductContainer;
import container.SalesContainer;
import exception.StorageException;
import model.*;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * SalesController is a class which connects the SalesContainer and the TUI
 * package with the necessary methods
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.Sale;
 * @see container.SalesContainer;
 */
public class SalesController {
    private Sale sale;

    public SalesController() {
        this.sale = null;
    }

    /**
     * Creates a new sale for a given employees
     * @param employee The employee who made the sale
     */
    public void createSale(Employee employee) {
        sale = new Sale(employee);
    }

    /**
     * Finds a customer based on their card's code
     * @param cardCode This is the barcode on the back of the customer's card
     * @return This returns a number between 0-100 which represents the percentage of discount the customer gets
     */
    public int findCustomer(String cardCode) throws StorageException {
        Customer customer = CustomerContainer.getInstance().findCustomer(cardCode);
        int discount = 0;
        if (customer != null) {
            sale.setCustomer(customer);
            if (customer.getCustomerGroup() != null) {
                discount = customer.getCustomerGroup().getDiscount();
            } else {
                throw new StorageException("Customer Group not found.");
            }
        }
        return discount;
    }


    /**
     * Finds a product and checks if the given amount is available in stock.
     * If the product is found and the amount is available, it adds the item to
     * the sale
     * @param barCode This is the barcode located on the product
     * @param amount This is the amount to be checked
     * @return boolean This returns true if the product exists and the given amount is available and the product is added to the sale.
     */
    public void findProduct(String barCode, int amount) throws StorageException {
        ProductType productType = ProductContainer.getInstance().findProduct(barCode);
        if (productType == null) {
            throw new StorageException("Product not found.");
        }
        if (amount <= productType.getAmount()) {
            sale.addProduct(productType, amount);
        } else {
            throw new StorageException("Product amount not sufficient.");
        }
    }

    /**
     * Removes a given number of products based on the barcode of the product
     * @param barCode is the barcode of the product to be removed
     * @param amount is the amount of products to be removed
     * @throws StorageException when there is no product with the given barcode
     */
    public void removeProduct(String barCode, int amount) throws StorageException {
        ProductType productType = ProductContainer.getInstance().findProduct(barCode);
        if (productType == null) {
            throw new StorageException("Product not found.");
        } else {
            sale.removeProduct(productType, amount);
        }
    }

    /**
     * Finds all sales made by a customer
     * @param cardCode is the customer's card number
     * @return a LinkedList which contains the sales made by the customer
     */
    public LinkedList<Sale> getSales(String cardCode) {
        Customer customer = CustomerContainer.getInstance().findCustomer(cardCode);

        return customer.getSales();
    }

    /**
     * Finds all sales
     * @return a LinkedList which contains all the sales ever made
     */
    public LinkedList<Sale> getSales() {
        return SalesContainer.getInstance().getSales();
    }

    public Sale getSale() {
        return this.sale;
    }

    /**
     * Saves a sale when everything is finished by the sales assistant / salesman
     * @param order if it's true, the sale is an order, if false it's a normal sale
     * @throws StorageException if there are no items added to the sale.
     */
    public void saleFinished(boolean order) throws StorageException {
        double totalPrice = 0;
        if (sale.getItems().isEmpty()) {
            throw new StorageException("No items were found.");
        } else {
            for (SaleItem item : sale.getItems()) {
                item.getProduct().removeAmount(item.getAmount());
                totalPrice += item.getPrice();
            }
        }

        Customer customer = sale.getCustomer();

        if (customer != null) {
            double discount = customer.getCustomerGroup().getDiscount();
            totalPrice -= totalPrice * (discount / 100);
        }
        sale.setTotalPrice(totalPrice);
        sale.setCreated(LocalDate.now());
        if (order) {
            sale.setOrder(true);
            sale.setOrderPicked(false);
        } else {
            sale.setOrder(false);
            sale.setOrderPicked(true);
        }
        SalesContainer.getInstance().addSale(sale);
    }

    /**
     * When a customer comes to pick up an order, this method is called
     * @param id the id of the sale to be picked up
     */
    public void pickOrder(int id) {
        Sale sale = SalesContainer.getInstance().getSale(id);
        sale.setOrderPicked(true);
    }

    /**
     * If the customer is not satisfied and returns the sale, this method is called
     * @param sale is the Sale to be removed
     */
    public void returnSale(Sale sale) {
        for (SaleItem item : sale.getItems()) {
            item.getProduct().addAmount(item.getAmount());
        }
        SalesContainer.getInstance().removeSale(sale);
    }
}
