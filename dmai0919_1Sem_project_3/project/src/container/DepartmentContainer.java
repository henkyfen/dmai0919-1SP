package container;

import model.Department;

import java.util.LinkedList;

/*
 * DepartmentContainer is a singleton container which contains a LinkedList
 * of the departments of the company
 * @see model.Department
 */
public class DepartmentContainer {
    private static DepartmentContainer instance;
    private final LinkedList<Department> departments;

    private DepartmentContainer() {
        departments = new LinkedList<>();
    }

    public static DepartmentContainer getInstance() {
        if (instance == null) {
            instance = new DepartmentContainer();

        }
        return instance;
    }

    /*
     * This method is used to add a new department to the container.
     * @param department	This is the department to be added to the container
     */
    public void addDepartment(String name) {
        Department department = new Department(name);
        departments.add(department);
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public boolean removeDepartment(Department department) {
        return departments.remove(department);
    }

    public LinkedList<Department> getDepartments() {
        return departments;
    }
    
    public Department getDepartment(String name) {
    	for (Department department : departments) {
			if (department.getName().equals(name)) {
				return department;
			}
		}
    	return null;
    }
}
