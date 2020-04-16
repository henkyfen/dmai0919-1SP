package model;

/**
 * Manager is a subclass of Employee which holds all information about the
 * managers of the company. They have access to everything in the system
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class Manager extends Employee {

    public Manager(String name, String login, String password, String phoneNumber, double salary) {
        super(name, login, password, phoneNumber, salary);
    }

}
