package main.model;

/**
 * A Department is composed of [1] Manager
 * 
 * @author Addison Woody
 *
 */
public class Department {

	private final Manager manager;
	
	public Department(Manager manager) {
		this.manager = manager;
	}
	
	/**
	 * Returns the head Manager of <strong>this</strong> Department
	 * 
	 * @return Manager
	 */
	public Manager getManager() {
		return manager;
	}
}
