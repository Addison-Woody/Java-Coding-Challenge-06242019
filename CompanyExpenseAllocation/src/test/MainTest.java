package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import main.model.Department;
import main.model.Developer;
import main.model.Employee;
import main.model.Manager;
import main.model.Organization;
import main.model.QATester;
import main.util.ExpenseCalculation;
import test.util.TestScenarioSetupUtil;

public class MainTest {

	/**
	 * UseCase: Developers warrant an allocation of $2000 each.
	 */
	@Test
	public void developerExpenseAllocationCheck() {

		//expected
		BigDecimal expectedAllocation = new BigDecimal(2000);

		//setup
		Employee qaTester = new Developer();

		//assertion
		assertTrue(qaTester instanceof Developer);
		assertEquals(expectedAllocation, qaTester.getExpenseAllocation());
	}
	
	/**
	 * UseCase: QATesters warrant an allocation of $1000 each.
	 */
	@Test
	public void qaTesterExpenseAllocationCheck() {

		//expected
		BigDecimal expectedAllocation = new BigDecimal(1000);

		//setup
		Employee qaTester = new QATester();

		//assertion
		assertTrue(qaTester instanceof QATester);
		assertEquals(expectedAllocation, qaTester.getExpenseAllocation());
	}
	
	/**
	 * UseCase: Managers warrant an allocation of $600 each.
	 */
	@Test
	public void managerExpenseAllocationCheck() {

		//expected
		BigDecimal expectedAllocation = new BigDecimal(600);

		//setup
		Employee qaTester = new Manager();

		//assertion
		assertTrue(qaTester instanceof Manager);
		assertEquals(expectedAllocation, qaTester.getExpenseAllocation());
	
	}
	
	/**
	 * UseCase: Managers can have QA Testers, Developers,
	 * and other managers report to them.
	 */
	@Test
	public void managersCanHaveQATesterDeveloperManagerCheck() {

		//expected
		Employee manager = new Manager();
		Employee developer = new Developer();
		Employee qaTester = new QATester();

		//setup
		Employee headManager = new Manager();
		headManager.addReportingEmployee(manager);
		headManager.addReportingEmployee(developer);
		headManager.addReportingEmployee(qaTester);
		Set<Employee> reportingEmployees = headManager.getReportingEmployees();

		//assertion
		assertTrue(reportingEmployees.contains(manager));
		assertTrue(reportingEmployees.contains(developer));
		assertTrue(reportingEmployees.contains(qaTester));
	}
	
	/**
	 * UserCase (Implied): QA Testers cannot have reports.
	 */
	@Test(expected = IllegalStateException.class)
	public void qaTestersNoReportingEmployeeCheck() {
		
		//expected (IllegalStateException)
		
		//setup
		Employee qaTester = new QATester();
		Employee developer = new Developer();
		qaTester.addReportingEmployee(developer);

		//assertion (expect IllegalStateException)
	}
	
	/**
	 * UserCase (Implied): Developers cannot have reportingEmployees.
	 */
	@Test(expected = IllegalStateException.class)
	public void developersNoReportingEmployeeCheck() {
		
		//expected (IllegalStateException)
		
		//setup
		Employee developer = new Developer();
		Employee qaTester = new QATester();
		developer.addReportingEmployee(qaTester);

		//assertion (expect IllegalStateException)
	}
	
	/**
	 * UserCase (Implied): Managers cannot manage themselves.
	 */
	@Test(expected = IllegalStateException.class)
	public void managersNoSelfManageCheck() {
		
		//expected (IllegalStateException)
		
		//setup
		Employee manager = new Manager();
		manager.addReportingEmployee(manager);

		//assertion (expect IllegalStateException)
	}
	
	/**
	 * UseCase: ExpenseAllocation total
	 * 
	 * For the given hierarchy:
	 * A. Manager A
	 *    I. Manager B
	 *       1. Developer A
	 *       2. QATester A
	 */
	@Test
	public void managerAExpenseAllocationCheck() {
		
		//expected
		BigDecimal expectedExpenseAllocation = new BigDecimal(4200);
		
		//setup
		Employee managerA = TestScenarioSetupUtil.createScenarioDepartmentA().getManager();
		BigDecimal organizationExpenseAllocation = ExpenseCalculation.sumExpenseAllocations(managerA);
		
		//assertion
		assertEquals(expectedExpenseAllocation, organizationExpenseAllocation);
	}
	
	/**
	 * UseCase: ExpenseAllocation total
	 * 
	 * For the given hierarchy:
	 *    I. Manager B
	 *       1. Developer A
	 *       2. QATester A
	 */
	@Test
	public void managerBExpenseAllocationCheck() {
		
		//expected
		BigDecimal expectedExpenseAllocation = new BigDecimal(3600);
		
		//setup
		Employee managerB = TestScenarioSetupUtil.createScenarioManagerB();
		BigDecimal organizationExpenseAllocation = ExpenseCalculation.sumExpenseAllocations(managerB);
		
		//assertion
		assertEquals(expectedExpenseAllocation, organizationExpenseAllocation);
	}
	
	/**
	 * UseCase: ExpenseAllocation total
	 * 
	 * For the given hierarchy:
	 * B. Manager C
	 *    I. Manager D
	 */
	@Test
	public void managerCExpenseAllocationCheck() {
		
		//expected
		BigDecimal expectedExpenseAllocation = new BigDecimal(1200);
		
		//setup
		Employee managerC = TestScenarioSetupUtil.createScenarioDepartmentB().getManager();
		BigDecimal organizationExpenseAllocation = ExpenseCalculation.sumExpenseAllocations(managerC);
		
		//assertion
		assertEquals(expectedExpenseAllocation, organizationExpenseAllocation);
	}
	
	/**
	 * UseCase: ExpenseAllocation total
	 * 
	 * For the given hierarchy:
	 *    I. Manager D
	 */
	@Test
	public void managerDExpenseAllocationCheck() {
		
		//expected
		BigDecimal expectedExpenseAllocation = new BigDecimal(600);
		
		//setup
		Employee managerD = new Manager("ManagerD");
		BigDecimal managerCExpenseAllocation = ExpenseCalculation.sumExpenseAllocations(managerD);
		
		//assertion
		assertEquals(expectedExpenseAllocation, managerCExpenseAllocation);
	}
	
	/**
	 * UseCase: ExpenseAllocation total
	 * 
	 * For the given hierarchy:
	 * C. Manager E
	 *    I. Developer B
	 */
	@Test
	public void managerEExpenseAllocationCheck() {
		
		//expected
		BigDecimal expectedExpenseAllocation = new BigDecimal(2600);
		
		//setup
		Employee managerE = TestScenarioSetupUtil.createScenarioDepartmentC().getManager();
		BigDecimal managerCExpenseAllocation = ExpenseCalculation.sumExpenseAllocations(managerE);
		
		//assertion
		assertEquals(expectedExpenseAllocation, managerCExpenseAllocation);
	}
	
	/**
	 * UseCase: ExpenseAllocation total
	 * 
	 * For the given hierarchy:
	 * A. Manager A
	 *    I. Manager B
	 *       1. Developer A
	 *       2. QATester A
	 *       
	 * B. Manager C
	 *    I. Manager D
	 *    
	 * C. Manager E
	 *    I. Developer B
	 */
	@Test
	public void exampleOrganizationScenario() {
		
		//expected
		BigDecimal expectedExpenseAllocation = new BigDecimal(8000);
		
		//setup
		Set<Department> departments = new HashSet<Department>();
		Department departmentA = TestScenarioSetupUtil.createScenarioDepartmentA();
		departments.add(departmentA);
		Department departmentB = TestScenarioSetupUtil.createScenarioDepartmentB();
		departments.add(departmentB);
		Department departmentC = TestScenarioSetupUtil.createScenarioDepartmentC();
		departments.add(departmentC);
		Organization organization = TestScenarioSetupUtil.createScenarioOrganization(departments);
		BigDecimal organizationExpenseAllocation = ExpenseCalculation.sumExpenseAllocations(organization);
		
		//assertion
		assertEquals(expectedExpenseAllocation, organizationExpenseAllocation);
	}
	
	/**
	 * UseCase: List any managers who have no people reporting to them
	 * 
	 * For the given hierarchy:
	 * A. Manager A
	 *    I. Manager B
	 *       1. Developer A
	 *       2. QATester A
	 *       
	 * B. Manager C
	 *    I. Manager D
	 *    
	 * C. Manager E
	 *    I. Developer B
	 */
	@Test
	public void noManagersWithReportingEmployeeList() {
		
		//expected
		Set<String> expectedManagers = new HashSet<String>();
		expectedManagers.add("Manager D");
		
		//setup
		Set<Department> departments = new HashSet<Department>();
		Department departmentA = TestScenarioSetupUtil.createScenarioDepartmentA();
		departments.add(departmentA);
		Department departmentB = TestScenarioSetupUtil.createScenarioDepartmentB();
		departments.add(departmentB);
		Department departmentC = TestScenarioSetupUtil.createScenarioDepartmentC();
		departments.add(departmentC);
		Organization organization = TestScenarioSetupUtil.createScenarioOrganization(departments);
		Set<String> managersWithoutReportingEmployees = new HashSet<String>();
		
		for (Department department : organization.getDepartments()) {
			for (Employee employee : department.getManager().getReportingEmployees()) {
				if(employee instanceof Manager && employee.getReportingEmployees().isEmpty()) {
					managersWithoutReportingEmployees.add(employee.getEmployeeName());
				}//end if
			}//end employee for
		}//end department for
		
		//assertion
		assertEquals(expectedManagers, managersWithoutReportingEmployees);
	}
}
