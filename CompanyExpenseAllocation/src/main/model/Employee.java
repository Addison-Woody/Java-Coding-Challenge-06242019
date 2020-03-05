package main.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * An Employee has an EXPENSE_ALLOCATION and
 * [0..*] reporting Employee(s)
 * 
 * @author Addison Woody
 *
 */
public abstract class Employee {

	protected Set<Employee> reportingEmployees = new HashSet<Employee>();
	
	/**
	 * Returns Expense Allocation amount for <strong>this</strong> Employee.
	 * 
	 * @return BigDecimal
	 */
	public abstract BigDecimal getExpenseAllocation();
	
	/**
	 * Adds <i>Employee</i> to <strong>this</strong>
	 * Employee's reportingEmployees
	 * 
	 * @param employee
	 */
	public abstract void addReportingEmployee(Employee employee);
	
	/**
	 * Returns the Employee(s) who report to <strong>this</strong> Employee.
	 * 
	 * @return Set<Employee>
	 */
	public Set<Employee> getReportingEmployees() {
		return reportingEmployees;
	}
	
	/**
	 * Returns the Employee name
	 * [Note]: added for visual compare on testMethods,
	 * could be replaced with a generated form of
	 * authentication
	 * 
	 * @return String
	 */
	public abstract String getEmployeeName();
	
}
