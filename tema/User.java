package tema;
import java.util.*;

public class User extends Consumer implements Observer{
	ArrayList<String> interestedCompanies = new ArrayList<>();
	ArrayList<Notification> notifications = new ArrayList<>();
	
	User(Resume resume, ArrayList<Consumer> acquaintances) {
		this.resume = resume;
		this.acquaintances = acquaintances;
	}

	User(Information info, ArrayList<Education> edu, ArrayList<Experience> exp) throws ResumeIncompleteException {
 		super(info, edu, exp);
 	}
	
	User(Resume resume) {
		this.resume = resume;
	}
	
	User(Information info) {
		this.resume.info = info;
		resume.edu = new ArrayList<>();
		resume.exp = new ArrayList<>();
	}
	
	public void addInterestedCompanies(ArrayList<String> stringInterestedCompanies) {
		interestedCompanies = stringInterestedCompanies;
	}
	
	public Double getTotalScore() {
		return 1.5 * getExperienceYears() + meanGPA();
	}
	
	public Employee convert() {
		for(String str : interestedCompanies)
			Application.getInstance().getCompany(str).getObservers().remove(this);
		this.update(new Notification("Ai fost angajat!"));
		return new Employee(resume, acquaintances);
	}
	
	public void update(Notification notification) {
		notifications.add(notification);
	}
	
	public String getUserAllInfo() {
		//Utilizat atunci cand afisez toate in formatiile despre utilizator
		//	in cadrul UserPage-ului.
		String s = "";
		
		s += "\n    Personal information:";
		s += "\n        Name: " + toString() +
			"\n        mail: " + resume.info.getEmail() +
			"\n        phone: " + resume.info.getPhone() +
			"\n        birthday: " + resume.info.getBday().toString() +
			"\n        gender: " + resume.info.getSex() +
			"\n        languages: " + resume.info.getLanguages() +
			"\n        lanaguages level: " + resume.info.getLanguagesLvl() +
			"\n    Education: ";
		for(Education edu : resume.edu) {
			s += "\n        " + edu.name +
				"\n            level: " + edu.level +
				"\n            start date: " + edu.start.toString();
			if(edu.finish != null)
				s += "\n            end date: " + edu.finish.toString();
			else
				s += "\n            end date: null";
			s += "\n            grade: " + edu.mean;
		}
		s += "\n    Experience: ";
		for(Experience exp : resume.exp) {
			s += "\n        " + exp.company +
				"\n            position: " + exp.position +
				"\n            start date: " + exp.start.toString();
			if(exp.finish != null)
				s += "\n            end date: " + exp.finish.toString();
			else
				s += "\n            end date: null";
		}
		
		return s;
	}
	
	public String toString() {
		return resume.info.getFirstName() + " " + resume.info.getLastName();
	}
	
}
