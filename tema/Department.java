package tema;
import java.util.*;
public abstract class Department {
	private ArrayList<Employee> employees = new ArrayList<>();
	private ArrayList<Job> jobs = new ArrayList<>();
	
	public abstract double getTotalSalaryBudget();
	
	public Job getJob(String jobName) {
		for(Job job : jobs)
			if(job.name.equals(jobName))
				return job;
		return null;
	}
	
	public ArrayList<Job> getJobs() {
		ArrayList<Job> availableJobs = new ArrayList<>();
		
		for(Job job : jobs) 
			if(job.flag == true) 
				availableJobs.add(job);
		return availableJobs;
	}
	
	public ArrayList<Job> getAllJobs() {
		return jobs;
	}
	
	public void add(Employee employee) {
		employees.add(employee);
	}
	
	public void addAll(ArrayList<Employee> newEmployees) {
		employees.addAll(newEmployees);
	}
	
	public void add(Job job) {
		jobs.add(job);
	}
	
	public boolean contains(String jobName) {
		for(Job job : jobs) 
			if(jobName.equals(job.name))
				return true;
		return false;
	}
	
	public void remove(Employee employee) {
		employees.remove(employee);
	}
	
	public void removeAllEmployees() {
		employees = new ArrayList<>();
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
}
