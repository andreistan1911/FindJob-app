package tema;
import java.util.*;
public class Job {
	String name, company;
	Constraint<Integer> graduateYear, experienceYears;
	Constraint<Double> mean;
	boolean flag = false; //open or not
	ArrayList<User> candidates = new ArrayList<>();
	ArrayList<Employee> employeed = new ArrayList<>();
	int noPositions = 0, salary;
	
	Job(String name) {
		this.name = name;
	}
	
	Job(String name, String company, int salary) {
		this.name = name;
		this.company = company;
		this.salary = salary;
	}
	
	Job(String name, String company, int salary, int noPositions, Integer gycLow, Integer gycUp,
			Integer eycLow, Integer eycUp, Double mcLow, Double mcUp) {
		this(name, company, salary);
		flag = true;
		graduateYear = new Constraint<>(gycLow, gycUp);
		experienceYears = new Constraint<>(eycLow, eycUp);
		mean = new Constraint<>(mcLow, mcUp);
	}
	
	public void apply(User user) {
		Application.getInstance().getCompany(company).getRecruiter(user).evaluate(this, user);
	}
	
	public boolean meetsRequirments(User user) {	
		if(graduateYear.check(user.getGraduationYear()) ||
			experienceYears.check(user.getExperienceYears()) ||
			mean.check(user.resume.edu.get(0).mean))
			return true;
		return false;
	}
}
