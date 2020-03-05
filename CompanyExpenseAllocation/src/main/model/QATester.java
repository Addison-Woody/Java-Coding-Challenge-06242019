package main.model;

import java.math.BigDecimal;

public class QATester extends Employee {

	private static final BigDecimal EXPENSE_ALLOCATION = new BigDecimal(1000);
	private String employeeName;
	
	public QATester() {
	}
	
	public QATester(String name) {
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
		//QATesters do not have reporting employees
		throw new IllegalStateException("Cannot add " + employee.getClass().getSimpleName() + " to " + this.getClass().getSimpleName());
	}
}