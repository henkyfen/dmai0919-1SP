package tui;


import controller.EmployeeController;
import model.*;

/**
 * LoginMenu is the first menu a user sees when they open the software
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class LoginMenu {
    EmployeeController employeeController;

    /**
     * This method is the "main" method of this class, it opens the menu,
     * displays the possible options and waits until a correct option is entered
     */
    public void start() {
        employeeController = new EmployeeController();
        boolean running = true;
        while (running) {
            int choice = writeLoginMenu();
            switch (choice) {
                case 0: {
                    running = false;
                    System.out.println("Good bye");
                    break;
                }
                case 1: {
                    createLoginMenu();
                    break;
                }
            }
        }
    }

    /*
     * This method checks the entered login credentials against the
     * EmployeeContainer and if it finds a match, returns the found employee
     * @param	login	This is the employee's username
     * @param	password	This is the employee's password
     * @return Employee	This returns the employee if found, null if not found
     * @see EmployeeContainer
     */
    private Employee checkLogin(String login, String password) {
        return employeeController.findEmployee(login, password);
    }

    /*
     * This method prints out the menu options for the main start() method
     * @return	int	returns the user's choice
     * @see LoginMenu.start()
     */
    private int writeLoginMenu() {
        // created a keyboard object to read input
        TextOptions menu = new TextOptions("\n ***** Vestbjerg Byggecenter *****", "Quit");
        menu.addOption("Login");

        return menu.prompt();
    }

    /*
     * This method prints out the login menu where the user can enter their
     * credentials and if the entered credentials are valid, it open's the
     * appropriate menu according to the user's group
     * @see LoginMenu.checkLogin()
     * @see SaleMenu
     * @see ManagementMenu
     * @see WarehouseMenu
     * @see OfficerMenu
     */
    private void createLoginMenu() {
        Employee employee;
        do {
            String login = TextInput.inputString("Login name");
            String password = TextInput.inputString("Password");
            employee = checkLogin(login, password);
            if (employee == null) {
                System.out.println("Invalid credentials! Please try again.");
            }
        }
        while (employee == null);

        if (employee instanceof Manager) new ManagementMenu().start();
        else if (employee instanceof SalesAssistant) new SaleMenu().start(employee);
        else if (employee instanceof SalesMan) new SaleMenu().start(employee); //\TODO
        else if (employee instanceof WarehouseWorker) new WarehouseMenu().start((WarehouseWorker) employee);
        else if (employee instanceof Officer) new OfficerMenu().start();
    }
}
