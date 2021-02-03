package tema;
public class Marketing extends Department{
	public double getTotalSalaryBudget() {
		double totalSalary = 0;
		
		for(Employee employee : getEmployees()) {
			if(employee.salary > 5000)
				totalSalary += 100.0/90 * employee.salary;
			else if(employee.salary < 3000)
				totalSalary += employee.salary;
			else
				totalSalary += 100.0/84 * employee.salary;
		}
		return totalSalary;
	}
}
