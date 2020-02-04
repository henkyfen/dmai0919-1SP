package tui;

import container.*;
import model.*;

/**
 * ContentGenerator is a class which fills the containers with some content for testing purposes.
 * The data which is imported into the containers are printed out to std-out.
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class ContentGenerator {

    public static void main(String[] args) {
        generate();
        new LoginMenu().start();
    }

    public static void generate() {
        Department kitchenDepartment = new Department("Kitchen department");
        Department timber = new Department("Timber department");

        SalesAssistant sales = new SalesAssistant("Sales Assistant", "sales", "password", "12345678", 20000);
        sales.addDepartment(kitchenDepartment);
        kitchenDepartment.addEmployee(sales);

        WarehouseWorker warehouse = new WarehouseWorker("Warehouse Worker", "warehouse", "password", "12345678", 15000);
        timber.addEmployee(warehouse);
        warehouse.addDepartment(timber);

        Manager manager = new Manager("Manager", "manager", "password", "12345678", 1000000);

        Officer officer = new Officer("Officer", "officer", "password", "12345678", 100000);

        DepartmentContainer.getInstance().addDepartment(kitchenDepartment);
        DepartmentContainer.getInstance().addDepartment(timber);
        EmployeeContainer.getInstance().addEmployee(sales);
        EmployeeContainer.getInstance().addEmployee(warehouse);
        EmployeeContainer.getInstance().addEmployee(manager);
        EmployeeContainer.getInstance().addEmployee(officer);

        Product nail1 = new Product("Nail 500", "Some nail ur gonna need", 125.50, "1", 100);
        Product nail2 = new Product("Nail 100", "Some nails ur gonna need also", 200, "2", 50);

        ProductBundle nails = new ProductBundle("NAILS YOU WANT", "meh", 300, "12");
        nails.addProduct(nail1);
        nails.addProduct(nail2);

        ProductContainer pc = ProductContainer.getInstance();
        pc.addProduct(nail1);
        pc.addProduct(nail2);
        pc.addProduct(nails);

        LeaseProductDescriptor descriptor = new LeaseProductDescriptor("Drill", "not necessary", 1000);
        LeaseProduct drill1 = new LeaseProduct("1", false, descriptor);
        LeaseProduct drill2 = new LeaseProduct("2", false, descriptor);

        descriptor.addLeaseProduct(drill1);
        descriptor.addLeaseProduct(drill2);

        LeaseProductContainer.getInstance().addLeaseProduct(descriptor);

        for (ProductType product : ProductContainer.getInstance().getProducts()) {
            System.out.println(product.printInfo());
        }
        for (Employee employee : EmployeeContainer.getInstance().getAll()) {
            System.out.println("Login: " + employee.getLogin() + "  Password: " + employee.getPassword());
        }
        for (Department department : DepartmentContainer.getInstance().getDepartments()) {
            System.out.println(department.getName());
        }

        Customer rob = new Customer("Rob Halford", "09090908", "rob@judaspriest.com", "idk", "123");
        CustomerGroup priestMembers = new CustomerGroup("Priest members");
        priestMembers.setDiscount(20);
        priestMembers.addCustomer(rob);
        rob.setCustomerGroup(priestMembers);

        Customer bruce = new Customer("Bruce Dickinson", "01010101", "bruce@ironmaiden.com", "idk also", "666");
        CustomerGroup maidenMembers = new CustomerGroup("Maiden members");
        maidenMembers.setDiscount(15);
        maidenMembers.addCustomer(bruce);
        bruce.setCustomerGroup(maidenMembers);

        CustomerContainer.getInstance().addCustomer(rob);
        CustomerGroupContainer.getInstance().addCustomerGroup(priestMembers);
        CustomerContainer.getInstance().addCustomer(bruce);
        CustomerGroupContainer.getInstance().addCustomerGroup(maidenMembers);

        for (Customer customer : CustomerContainer.getInstance().getCustomers()) {
            System.out.println(customer.getTitle());
        }
    }

}
