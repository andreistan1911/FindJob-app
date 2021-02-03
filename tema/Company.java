package tema;
import java.util.*;
public class Company implements Subject{
	String name;
	Manager manager;
	int noAvailableJobs = 0;
	int noEmployees = 0;
	private ArrayList<Employee> employees = new ArrayList<>();
	private ArrayList<Department> departments = new ArrayList<>();
	private ArrayList<Recruiter> recruiters = new ArrayList<>();
	private ArrayList<User> observers = new ArrayList<>();
	
	Company(String name) {
		this.name = name;
		manager = null;
	}
	
	Company(String name, Manager manager) {
		this.name = name;
		this.manager = manager;
	}
	
	public ArrayList<User> getObservers(){
		return observers;
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	public ArrayList<Recruiter> getRecruiters() {
		return recruiters;
	}
	
	public void add(Department department) {
		departments.add(department);
	}
	
	public void add(Recruiter recruiter) {
		recruiters.add(recruiter);
	}
	
	public void add(Employee employee, Department department) {
		employees.add(employee);
		departments.get(departments.indexOf(department)).add(employee);
	}
	
	public void remove(Employee employee) {
		employees.remove(employee);
		for(Department department : departments)
			if(department.getEmployees().contains(employee)) {
				department.remove(employee);
				return;
			}
	}
	
	public void remove(Department department) {
		departments.remove(department);
	}
	
	public void remove(Recruiter recruiter) {
		recruiters.remove(recruiter);
	}
	
	public void move(Department source, Department destination) {
		destination.addAll(source.getEmployees());
		source.removeAllEmployees();
	}
	
	public void move(Employee employee, Department newDepartment) {
		remove(employee);
		departments.get(departments.indexOf(newDepartment)).add(employee);
	}
	
	public boolean contains(Department department) {
		return departments.contains(department);
	}
	
	public boolean contains(Employee employee) {
		for(Department department : departments)
			if(department.getEmployees().contains(employee))
				return true;
		return false;
	}
	
	public boolean contains(Recruiter recruiter) {
		return recruiters.contains(recruiter);
	}
	
	public Recruiter getRecruiter(User user) {
		int max = 0;
		Recruiter theChosenOne = recruiters.get(0);
		for(Recruiter recruiter : recruiters) {
			int degree = user.getDegreeInFriendship(recruiter);
			if(max < degree) {
				max = degree;
				theChosenOne = recruiter;
			}
		}
		return theChosenOne;
	}
	
	public ArrayList<Department> getDepartments() {
		return departments;
	}
	
	public Department getDepartment(String departmentName) {
		for(Department department : departments) {
			String departmentClass = department.getClass().toString().split(" |\\.", 3)[2];
			if(departmentClass.equals(departmentName))
				return department;
		}
		return null;
	}
	
	public ArrayList<Job> getJobs() {
		ArrayList<Job> jobs = new ArrayList<>();
		
		for(Department department : departments)
			jobs.addAll(department.getJobs());
		return jobs;
	}
	
	public ArrayList<Job> getAllJobs() {
		ArrayList<Job> jobs = new ArrayList<>();
		
		for(Department department : departments)
			jobs.addAll(department.getAllJobs());
		return jobs;
	}	
	
	public void addObserver(User user) {
		observers.add(user);
	}
	
	public void removeObserver(User user) {
		observers.remove(user);
	}
	
	public void notifyAllObservers() {
		for(User user : observers) {
			user.update(new Notification("S-a deschis/inchis un job la compania" + name));
		}
	}
	
}
