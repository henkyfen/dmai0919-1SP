package tui;

import controller.LeaseController;
import controller.SalesController;
import exception.StorageException;
import model.Employee;
import model.Lease;
import model.Sale;

import java.util.LinkedList;

/**
 * SaleMenu is the TUI menu which shows for the SalesAssistant and Salesman employees.
 *
 * @author dmai0919/Group3
 * @version 1.0
 * @see model.SalesAssistant
 * @see model.SalesMan
 * @since 2019-12-07
 */
public class SaleMenu {
    private Employee employee;
    private SalesController salesController;
    private LeaseController leaseController;


    public void start(Employee employee) {
        salesController = new SalesController();
        leaseController = new LeaseController();
        this.employee = employee;
        boolean running = true;
        while (running) {
            int choice = writeSaleMenu();
            switch (choice) {
                case 0: {
                    running = false;
                    break;
                }
                case 1: {
                    createSaleMenu();
                    break;
                }
                case 2: {
                    createLeaseMenu();
                    break;
                }
                case 3: {
                    System.out.println("All sales:");
                    LinkedList<Sale> sales = salesController.getSales();
                    for (Sale sale : sales) {
                        System.out.println(sale.getInfo());
                    }
                    System.out.println("---------\n");
                    break;
                }
                case 4: {
                    System.out.println("All leases:");
                    LinkedList<Lease> leases = leaseController.getLeases();
                    for (Lease lease : leases) {
                        System.out.println(lease.getInfo());
                    }
                    System.out.println("---------\n");
                    break;
                }
                default: {
                    System.out.println("Wrong option");
                    break;
                }
            }
        }
    }

    private int writeSaleMenu() {
        // creates a keyboard object to read input
        TextOptions menu = new TextOptions("\n ***** Salesman menu *****", "Back");
        //\TODO Print out already added products
        menu.addOption("Create Sale");
        menu.addOption("Create Lease");
        menu.addOption("Show Sales");
        menu.addOption("Show Leases");

        return menu.prompt();
    }

    private void createSaleMenu() {
        this.salesController = new SalesController();
        salesController.createSale(this.employee);
        int choice = identifyCustomerMenu();

        switch (choice) {
            case 0:
                saleMenu();
                break;
            case 1:
                String cardCode = TextInput.inputString("Customers code card");
                int discount = 0;
                try {
                    discount = salesController.findCustomer(cardCode);
                } catch (StorageException exception) {
                    System.out.println(exception.getMessage());
                }
                if (discount != 0) {
                    System.out.println("Discount Applied! (" + discount + " %)");
                } else if (discount == 0) {
                    System.out.println("Customer not found, discount is 0!");
                }
                break;
            default:
                System.out.println("Customer without a card, discount is 0!");
                break;

        }
        boolean running = true;
        while (running) {
            choice = saleMenu();

            switch (choice) {
                case 0:
                    writeSaleMenu();
                    break;
                case 1:
                    String itemCode = TextInput.inputString("Scan Barcode");
                    String strAmount = TextInput.inputString("Amount");
                    int amount;

                    if (strAmount.equals("")) {
                        amount = 1;
                    } else {
                        amount = Integer.parseInt(strAmount);
                    }
                    try {
                        salesController.findProduct(itemCode, amount);
                        System.out.println("Product added");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;


                case 2:
                    String itemCode2 = TextInput.inputString("Scan Barcode");
                    String strAmount2 = TextInput.inputString("Amount");
                    int amount2;

                    if (strAmount2.equals("")) {
                        amount2 = 1;
                    } else {
                        amount2 = Integer.parseInt(strAmount2);
                    }
                    try {
                        salesController.removeProduct(itemCode2, amount2);
                    } catch (StorageException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 3:
                    try {
                        salesController.saleFinished(false);
                        System.out.println(salesController.getSale().getInfo());
                        running = false;
                    } catch (StorageException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 4:
                    try {
                        salesController.saleFinished(true);
                        System.out.println(salesController.getSale().getInfo());
                        running = false;
                    } catch (StorageException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                default:
                    System.out.println("invalid option, try again.");
            }
        }
    }

    private int identifyCustomerMenu() {
        TextOptions menu = new TextOptions("\n ***** Identify customer menu *****", "Continue with no Customer card");
        menu.addOption("Use Customer Card");
        return menu.prompt();
    }

    private int saleMenu() {
        TextOptions menu = new TextOptions("\n ***** Create sale menu *****", "Abort sale");
        menu.addOption("Add product");
        menu.addOption("Remove product");
        menu.addOption("Finish and pay");
        menu.addOption("Finish and order");
        return menu.prompt();
    }

    private int writeLeaseMenu() {
        TextOptions menu = new TextOptions("\n ***** Salesman menu *****", "Back");
        menu.addOption("Create Lease");
        menu.addOption("Return a lease");
        menu.addOption("Show Leases");

        return menu.prompt();
    }

    private void leaseMenu() {
        this.leaseController = new LeaseController();
        boolean running = true;
        int choice;
        while (running) {
            choice = writeLeaseMenu();

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    createLeaseMenu();
                    break;
                case 2:
                    returnLeaseMenu();
                    break;
                case 3:
                    showLeases();
                    break;
                default:
                    System.out.println("Wrong option, try again");
                    break;
            }
        }

    }

    private int writeCreateLeaseMenu() {
        TextOptions menu = new TextOptions("\n ***** Create lease menu *****", "Abort lease");
        menu.addOption("Add lease item");
        menu.addOption("Finish lease");

        return menu.prompt();
    }

    private int forceIdentifyCustomerMenu() {
        TextOptions menu = new TextOptions("\n ***** Identify customer menu *****", "Abort");
        menu.addOption("Scan customer card");

        return menu.prompt();
    }

    private void createLeaseMenu() {
        leaseController = new LeaseController();
        leaseController.createLease(employee);

        int choice;

        boolean customerChosen = false;
        boolean aborted = false;
        while (!customerChosen && !aborted) {
            choice = forceIdentifyCustomerMenu();

            switch (choice) {
                case 0:
                    break;
                case 1:
                    String cardCode = TextInput.inputString("Customers code card");
                    int discount;
                    try {
                        discount = leaseController.findCustomer(cardCode);
                        System.out.println("Discount Applied! (" + discount + " %)");
                        customerChosen = true;
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }

        boolean running = customerChosen;

        while (running) {
            choice = writeCreateLeaseMenu();

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    String code = TextInput.inputString("Lease product code");
                    try {
                        leaseController.findLeaseProduct(code);
                        System.out.println("Lease product added");

                    } catch (StorageException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 2:
                    try {
                        leaseController.leaseFinished();
                        running = false;
                        System.out.println("Lease completed");
                        break;
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    System.out.println("Wrong choice, try again");
                    break;
            }
        }

    }

    private void returnLeaseMenu() {
        boolean running = true;
        int choice;

        while (running) {
            choice = writeReturnLeaseMenu();

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    int id = TextInput.inputNumber("Lease ID");
                    try {
                        leaseController.returnLease(id);
                        System.out.println("Lease returned");
                    } catch (StorageException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }


    }

    private int writeReturnLeaseMenu() {
        TextOptions menu = new TextOptions("\n ***** Return Lease Menu *****", "Back");
        menu.addOption("Input Lease ID");
        return menu.prompt();
    }

    private void showLeases() {
        LinkedList<Lease> leases = leaseController.getLeases();

        for (Lease lease : leases) {
            System.out.println(lease.getInfo());
        }
    }

}

