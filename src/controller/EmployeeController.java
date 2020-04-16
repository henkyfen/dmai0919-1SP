package controller;

import container.EmployeeContainer;
import model.*;

import java.util.LinkedList;

/**
 * EmployeeController is a class which connects the EmployeeContainer and the TUI
 * package' classes with the necessary methods
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.Employee
 * @see container.EmployeeContainer
 */
public class EmployeeController {

    /*
     * This method finds employees based on their username and password
     * @param login This is the username of the employee
     * @param password This is the password of the employees
     * @return This returns the employee if there is a match. Returns null cannot be found
     */
    public Employee findEmployee(String login, String password) {
        return EmployeeContainer.getInstance().findEmployee(login, password);
    }

    public void removeEmployee(Employee employee) {
        EmployeeContainer.getInstance().removeEmployee(employee);
    }

    public LinkedList<Employee> getAll() {
        return EmployeeContainer.getInstance().getAll();
    }

    public Employee createEmployee(int type, String name, String login, String password, String phoneNumber, double salary) {
        //"Sales assistant", "Salesman", "Warehouse worker", "Officer", "Manager"
        Employee e;
        switch (type) {
            case 1:
                e = new SalesAssistant(name, login, password, phoneNumber, salary);
                break;
            case 2:
                e = new SalesMan(name, login, password, phoneNumber, salary);
                break;
            case 3:
                e = new WarehouseWorker(name, login, password, phoneNumber, salary);
                break;
            case 4:
                e = new Officer(name, login, password, phoneNumber, salary);
                break;
            case 5:
                e = new Manager(name, login, password, phoneNumber, salary);
                break;
            default:
                e = new Employee(name, login, password, phoneNumber, salary);
        }
        
        if(EmployeeContainer.getInstance().addEmployee(e)) {
        	return e;
        }else {
        	return null;
        }
        
    }

    public void updateEmployee(String name, String login, String password, String phoneNumber, double salary) {
        Employee e = EmployeeContainer.getInstance().findEmployee(login);
        e.setName(name);
        e.setLogin(login);
        e.setPassword(password);
        e.setPhoneNumber(phoneNumber);
        e.setSalary(salary);
    }
}
