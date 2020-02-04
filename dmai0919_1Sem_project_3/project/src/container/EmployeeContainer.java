package container;

import model.Employee;

import java.util.LinkedList;

/*
 * EmployeeContainer is a singleton container which contains a LinkedList
 * of the employees of the company
 * @see model.Employee
 */
public class EmployeeContainer {
    private static EmployeeContainer instance;
    private final LinkedList<Employee> employees;

    private EmployeeContainer() {
        employees = new LinkedList<>();
    }

    public static EmployeeContainer getInstance() {
        if (instance == null) {
            instance = new EmployeeContainer();

        }
        return instance;
    }

    /*
     * This method is used to add a new employee to the container.
     * @param employee	This is the employee to be added to the container
     * @return boolean	This returns true if the employee is added successfully
     */
    public boolean addEmployee(Employee e) {
    	for (Employee employee : employees) {
			if (employee.getLogin().equals(e.getLogin())) {
				return false;
			}
		}
        return employees.add(e);
    }

    /*
     * This method is used to find an employee based on their username and password
     * @param login	This is the username of the employee
     * @param password This is the password of the employee
     * @return Employee This returns the employee if found. Returns null if not found
     */
    public Employee findEmployee(String login, String password) {
        for (Employee employee : employees) {
            String l = employee.getLogin();
            String p = employee.getPassword();
            if (l.equals(login) && p.equals(password)) {
                return employee;
            }
        }
        return null;
    }

    public Employee findEmployee(String login) {
        for (Employee employee : employees) {
            String l = employee.getLogin();
            if (l.equals(login)) {
                return employee;
            }
        }
        return null;
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public LinkedList<Employee> getAll() {
        return employees;
    }
}
