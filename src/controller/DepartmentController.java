package controller;

import container.DepartmentContainer;
import model.Department;

import java.util.LinkedList;
/**
 * DepartmentController is a class which connects the DepartmentContainer and the GUI
 * package with the necessary methods
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2020-01-27
 * @see model.Department;
 * @see container.DepartmentContainer;
 */
public class DepartmentController {

    public void createDepartment(String name) {
        DepartmentContainer.getInstance().addDepartment(name);
    }

    public boolean removeDepartment(Department department) {
        return DepartmentContainer.getInstance().removeDepartment(department);
    }

    public LinkedList<Department> getDepartments() {
        return DepartmentContainer.getInstance().getDepartments();
    }
    
    public String[] getDepartmentNames() {
    	LinkedList<Department> departments = this.getDepartments();
    	String [] strings = new String[departments.size()];
    	for (int i = 0; i < strings.length; i++) {
			strings[i] = departments.get(i).getName();
		}
    	
    	return strings;
    }
    
    public Department getDepartment(String name) {
    	return DepartmentContainer.getInstance().getDepartment(name);
    }


}
