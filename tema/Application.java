package tema;
import java.util.*;
public class Application {
	private static Application single_instance = null;
	
	private ArrayList<Company> companies;
	private ArrayList<User> users;
	static int noConsumers = 0;
	static int noAvailableJobs = 0;
	
	private Application() {
		companies = new ArrayList<>();
		users = new ArrayList<>();
	}
	
	public static Application getInstance() {  
        if (single_instance == null) 
            single_instance = new Application();
        return single_instance; 
    } 
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public User getUser(String firstName, String lastName) {
		for(User user : users)
			if(user.resume.info.getFirstName().equals(firstName) &&
			   user.resume.info.getLastName().equals(lastName))
				return user;
		return null;
	}
	
	public User getUser(String name) {
		for(User user : users) {
			if(user.toString().equals(name))
				return user;
		}
		return null;
	}
	
	public void removeUser(String firstName, String lastName) {
		for(User user : users)
			if(user.resume.info.getFirstName().equals(firstName) &&
			   user.resume.info.getLastName().equals(lastName)) {
				users.remove(user);
				return;
			}		
	}
	
	public void removeUser(String name) {
		for(User user : users)
			if(user.toString().equals(name)) {
				users.remove(user);
				return;
			}		
	}
	
	public ArrayList<Company> getCompanies() {
		return companies;
	}
	
	public Company getCompany(String name) {
		for(Company company : companies)
			if(company.name.equals(name))
				return company;
		return null;
	}
	
	public void add(Company company) {
		companies.add(company);
	}
	
	public void add(User user) {
		users.add(user);
	}
	
	public boolean remove(Company company) {
		if(companies.contains(company)) {
			companies.remove(company);
			return true;
		}
		return false;
	}
	
	public boolean remove(User user) {
		if(users.contains(user)) {
			users.remove(user);
			return true;
		}
		return false;
	}
	
	public ArrayList<Job> getJobs(List<String> companies) {
		//Returneaza job-urilee valabile
		ArrayList<Job> jobs = new ArrayList<>();
		for(String companyName : companies) {
			Company company = getCompany(companyName);
			if(company != null) 
				jobs.addAll(company.getJobs());
		}
		return jobs;
	}
	
	void resetLevels() {
		//Asta e pentru getDegreeInFriendShip din consumer
		for(int i = 0; i < users.size(); i++)
			users.get(i).level = 0;
		for(int i = 0; i < companies.size(); i++) {
			for(int j = 0; j < companies.get(i).getRecruiters().size(); j++)
				companies.get(i).getRecruiters().get(j).level = 0;
			for(int j = 0; j < companies.get(i).getDepartments().size(); j++)
				for(int k = 0; k < companies.get(i).getDepartments().get(j).getEmployees().size(); k++)
					companies.get(i).getDepartments().get(j).getEmployees().get(k).level = 0;
		}
	}
	
}
