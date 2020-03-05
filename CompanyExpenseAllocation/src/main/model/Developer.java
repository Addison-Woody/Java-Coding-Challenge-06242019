package main.model;

import java.math.BigDecimal;

public class Developer extends Employee {

	private static final BigDecimal EXPENSE_ALLOCATION = new BigDecimal(2000);
	private String employeeName;

	public Developer() {
	}
	
	public Developer(String name) {
		this.employeeName = name;
	}
	
	@Override
	public String getEmployeeName() {
		return employeeName;
	}
	
	@Override
	public BigDecimal getExpenseAllocation() {
		return EXPENSE_ALLOCATION;
	}

	@Override
	public void addReportingEmployee(Employee employee) {
		//Developers do not have reporting employees
		throw new IllegalStateException("Cannot add " + employee.getClass().getSimpleName() + " to " + this.getClass().getSimpleName());
	}
}
