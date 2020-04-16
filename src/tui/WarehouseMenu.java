package tui;

import controller.WarehouseController;
import exception.StorageException;
import model.WarehouseWorker;

/**
 * WarehouseMenu is the menu a user sees when after they log in and their group is WarehouseWorker
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class WarehouseMenu {
    private WarehouseWorker employee;
    private WarehouseController warehouseController;

    private int writeWarehouseMenu() {
        TextOptions menu = new TextOptions("\n ***** Warehouse menu *****", "Back");
        menu.addOption("Accept products");

        return menu.prompt();
    }

    public void start(WarehouseWorker employee) {
        this.employee = employee;
        //\TODO
        boolean running = true;
        int choice;

        while (running) {
            choice = writeWarehouseMenu();

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    acceptProducts();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void acceptProducts() {
        warehouseController = new WarehouseController();
        int choice;

        double price = TextInput.inputDouble("Price of accepted products");

        warehouseController.acceptOrder(employee, price);

        boolean running = true;
        String input;

        while (running) {
            choice = acceptProductMenu();
            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    input = TextInput.inputString("Scan products code");
                    int amount = TextInput.inputNumber("Product amount");
                    try {
                        warehouseController.acceptItem(input, amount);
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    input = TextInput.inputString("Scan products code");
                    try {
                        warehouseController.removeItem(input);
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        if (warehouseController.finishAccepting()) {
                            System.out.println("Products accepted");
                            running = false;
                        } else {
                            System.out.println("Can't finish accepting");
                        }
                        break;
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
        }
    }

    private int acceptProductMenu() {
        TextOptions menu = new TextOptions("\n ***** Accept products *****", "Abort");
        menu.addOption("Scan product");
        menu.addOption("Remove product");
        menu.addOption("Finish");

        return menu.prompt();
    }
}
