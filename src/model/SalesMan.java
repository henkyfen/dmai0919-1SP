package model;

/**
 * SalesMan is a subclass of Employee which holds all information about the
 * salesmen of the company. They have access to making new sales/orders
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class SalesMan extends Employee {
    private final double bonusSalary;

    public SalesMan(String name, String login, String password, String phoneNumber, double salary) {
        super(name, login, password, phoneNumber, salary);
        bonusSalary = 0;
    }

    //\TODO bonus salary thing

}
