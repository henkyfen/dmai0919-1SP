package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee is a superclass which holds all information about the
 * employees (users of the system).
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class Employee implements Renderable {
    private ArrayList<Department> departments;
    private String name;
    private String login;
    private String password;
    private String phoneNumber;
    private double salary;

    public Employee(String name, String login, String password, String phoneNumber, double salary) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
    	if (!departments.contains(department)) {
    		departments.add(department);
    	}
    }


    public ArrayList<Department> getDepartments() {
        return departments;
    }
    
    public void setDepartments(List<Department> departments) {
    	this.departments = (ArrayList<Department>) departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getTitle() {
        return name;
    }

}
