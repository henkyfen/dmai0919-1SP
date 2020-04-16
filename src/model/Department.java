package model;

import java.util.LinkedList;

/**
 * Department is a class which holds information about the different warehouses/
 * department of the company and all the employees who work there.
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class Department implements Renderable {
    private String name;
    private LinkedList<Employee> employees;
    private LinkedList<ProductType> products;


    public Department(String name) {
        this.name = name;
        employees = new LinkedList<>();
        products = new LinkedList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public boolean addProduct(ProductType product) {
        return products.add(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(LinkedList<Employee> employees) {
        this.employees = employees;
    }

    public LinkedList<ProductType> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<ProductType> products) {
        this.products = products;
    }

    public String getTitle() {
        return name;
    }
}
