package tui;

/**
 * ManagementMenu is the menu a user sees when after they log in and their group is Manager
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class ManagementMenu {
    public void start() {
        boolean running = true;
        while (running) {
            int choice = 0; //= writeManagementMenu();
            switch (choice) {
                case 0: {
                    running = false;
                    break;
                }
                case 1: {
                    //manageEmployeeAccounts();
                    break;
                }
                case 2: {
                    //changeCustomerGroupDiscounts();
                    //break;
                }
                case 3: {
                    //showSales();
                    //break;
                }
            }
        }
    }
    /*
    private void listEmployees() {
        EmployeeController container = new EmployeeController();
        System.out.println("List of registered employees:");
	//\TODO
    }
    */
}
