package tema;

public class Finance extends Department{
	public double getTotalSalaryBudget() {
		double totalSalary = 0;
		
		for(Employee employee : getEmployees()) {
			double taxes = 10;
			MyDate finishDate = employee.resume.exp.get(employee.resume.exp.size() - 1).finish;
			MyDate startDate = employee.resume.exp.get(0).start;
			
			if(employee.getExperienceYears() > 1 ||
				finishDate.month > startDate.month || 
				(finishDate.month == startDate.month && finishDate.day > startDate.day))
				taxes = 16;
			totalSalary += (100/(100-taxes) * employee.salary);	
		}
		
		return totalSalary;
	}
}
