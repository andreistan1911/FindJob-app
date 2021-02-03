package tema;
import java.util.*;

public class Employee extends Consumer {
	String company = "";
	int salary = 0;
	
	Employee() {
		super();
	}
	
	Employee(String company) {
		this.company = company;
	}
	
	Employee(String company, int salary) {
		this.company = company;
		this.salary = salary;
	}
	
	Employee(Information info, ArrayList<Education> edu, ArrayList<Experience> exp) throws ResumeIncompleteException {
		super(info, edu, exp);
	}
	
	Employee(Information info, ArrayList<Education> edu, ArrayList<Experience> exp, int salary) throws ResumeIncompleteException {
		super(info, edu, exp);
		this.salary = salary;
	}
	
	Employee(Resume resume, ArrayList<Consumer> acquaintances) {
		this.resume = resume;
		this.acquaintances = acquaintances;
	}
	
	Employee(Information info) {
		this.resume.info = info;
		this.resume.edu = new ArrayList<>();
		this.resume.exp = new ArrayList<>();
		this.acquaintances = new ArrayList<>();
	}
}
