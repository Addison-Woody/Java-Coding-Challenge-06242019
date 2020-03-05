package test.util;

import java.util.Set;

import main.model.Department;
import main.model.Developer;
import main.model.Employee;
import main.model.Manager;
import main.model.Organization;
import main.model.QATester;

public class TestScenarioSetupUtil {
		
	
		/**
		 * For the given hierarchy:
		 *    I. Manager B
		 *       1. Developer A
		 *       2. QATester A
		 */
		public static Manager createScenarioManagerB() {
			//Establish all the employees
			Manager managerB = new Manager();
			Employee developerA = new Developer();
			Employee qaTesterA = new QATester();
	
			//Set up reporting employee hierarchy
			managerB.addReportingEmployee(developerA);
			managerB.addReportingEmployee(qaTesterA);
			
			return managerB;
		}
	
		/**
		 * For the given hierarchy:
		 * A. Manager A
		 *    I. Manager B
		 *       1. Developer A
		 *       2. QATester A
		 */
		public static Department createScenarioDepartmentA() {
			//Establish all the employees
			Manager managerA = new Manager("Manager A");
			Manager managerB = new Manager("Manager B");
			Employee developerA = new Developer("Developer A");
			Employee qaTesterA = new QATester("QATester A");

			//Set up reporting employee hierarchy
			managerA.addReportingEmployee(managerB);
			managerB.addReportingEmployee(developerA);
			managerB.addReportingEmployee(qaTesterA);
			
			//Establish Departments, with head managers
			Department departmentA = new Department(managerA);
			
			return departmentA;
		}
		
		/**
		 * For the given hierarchy:
		 * B. Manager C
		 *    I. Manager D
		 */
		public static Department createScenarioDepartmentB() {
			//Establish all the employees
			Manager managerC = new Manager("Manager C");
			Manager managerD = new Manager("Manager D");

			//Set up reporting employee hierarchy
			managerC.addReportingEmployee(managerD);
			
			//Establish Departments, with head managers
			Department departmentB = new Department(managerC);
			
			return departmentB;
		}
		
		/**
		 * For the given hierarchy:
		 * C. Manager E
		 *    I. Developer B
		 */
		public static Department createScenarioDepartmentC() {
			//Establish all the employees
			Manager managerE = new Manager("Manager E");
			Employee developerB = new Developer("Developer B");

			//Set up reporting employee hierarchy
			managerE.addReportingEmployee(developerB);
			
			//Establish Departments, with head managers
			Department departmentC = new Department(managerE);
			
			return departmentC;
		}
	
		public static Organization createScenarioOrganization(Set<Department> departments) {
			Organization organization = new Organization();
			for (Department department : departments) {
				organization.addDepartment(department);
			}
			return organization;
		}

		
}
