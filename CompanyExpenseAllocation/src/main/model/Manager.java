package main.model;

import java.math.BigDecimal;
import java.util.Set;

public class Manager extends Employee{
	
	private static final BigDecimal EXPENSE_ALLOCATION = new BigDecimal(600);
	private String employeeName;
	
	public Manager() {
	}
	
	public Manager(String name) {
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
	
	public void addReportingEmployee(Employee employee) {
		if(this.equals(employee)) {
			throw new IllegalStateException("Cannot add " + employee.getClass().getSimpleName() + " to " + this.getClass().getSimpleName());
		}
		reportingEmployees.add(employee);
	}
	
	public Set<Employee> getReportingEmployees() {
		return reportingEmployees;
	}
}
