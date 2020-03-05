package main.model;

import java.util.HashSet;
import java.util.Set;

/**
 * An Organization is composed of [0..*] Departments
 * 
 * @author Addison Woody
 *
 */
public class Organization {
	
	private final Set<Department> departments = new HashSet<Department>();
	
	public Organization() {
	}
	
	/**
	 * Adds Department to <strong>this</strong> Organization
	 * 
	 * @param department
	 */
	public void addDepartment(Department department) {
		departments.add(department);
	}	
	
	/**
	 * Returns the Department(s) in <strong>this</strong> Organization.
	 * 
	 * @return Set<Department>
	 */
	public Set<Department> getDepartments() {
		return departments;
	}

}
