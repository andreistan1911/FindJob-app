package tema;
public class Management extends Department{
	public double getTotalSalaryBudget() {
		double totalSalary = 0;
		
		for(Employee employee : getEmployees()) 
			totalSalary += employee.salary;
		return 100.0/84 * totalSalary;
	}
}
