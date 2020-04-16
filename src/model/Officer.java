package model;

/**
 * Officer is a subclass of Employee which holds all information about the
 * officers of the company. They have access to finances and statistics
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 */
public class Officer extends Employee {

    public Officer(String name, String login, String password, String phoneNumber, double salary) {
        super(name, login, password, phoneNumber, salary);
    }

}
