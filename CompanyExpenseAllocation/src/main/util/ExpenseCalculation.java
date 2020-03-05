package main.util;

import java.math.BigDecimal;

import main.model.Department;
import main.model.Employee;
import main.model.Organization;

public class ExpenseCalculation {
	
	/**
	 * Recursively sums up the total Expense Allocation for the
	 * <i>Employee</i>, and all their reporting Employee(s)
	 * 
	 * @param employee
	 * @return BigDecimal
	 */
	public static BigDecimal sumExpenseAllocations(Employee employee) {
		BigDecimal total = employee.getExpenseAllocation();
		for (Employee reportingEmployee : employee.getReportingEmployees()) {
			//Recursive Call
			total = total.add(sumExpenseAllocations(reportingEmployee));
		}
		return total;
	}
	
	/**
	 * Sums up the total Expense Allocation for the <i>Department</i>
	 * 
	 * @param department
	 * @return BigDecimal
	 */
	public static BigDecimal sumExpenseAllocations(Department department) {
		return sumExpenseAllocations(department.getManager());
	}
	
	/**
	 * Sums up the total Expense Allocation for the <i>Organization</i>
	 * 
	 * @param department
	 * @return BigDecimal
	 */
	public static BigDecimal sumExpenseAllocations(Organization organization) {
		BigDecimal total = new BigDecimal(0);
		for (Department department : organization.getDepartments()) {
			total = total.add(sumExpenseAllocations(department));
		}
		return total;
	}

}
