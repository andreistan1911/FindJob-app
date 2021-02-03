package tema;
public class IT extends Department{
	public double getTotalSalaryBudget() {
		double totalSalary = 0;
		
		for(Employee employee : getEmployees()) 
			totalSalary += employee.salary;
		return totalSalary;
	}
}
