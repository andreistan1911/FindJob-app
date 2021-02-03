package tema;
import java.util.*;

class InvalidLevelException extends Exception {
	InvalidLevelException() {
		//oop
	}
	InvalidLevelException(String msg) {
		super(msg);
	}
}

public class Information {
	private String firstName, lastName, email, phone, sex;
	private MyDate bday;
	private ArrayList<String> languages, languagesLvl;
	
	Information() {
		firstName = lastName = email = phone = sex = "";
		bday = new MyDate();
	}
	
	Information(String firstName, String lastName, String email, String phone,
				String sex, MyDate bday, ArrayList<String> languages, ArrayList<String> languagesLvl) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.bday = bday;
		this.languages = languages;
		this.languagesLvl = languagesLvl;
	}
	
	Information(String firstName, String lastName, String email, String phone,
			String sex, MyDate bday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.bday = bday;
		this.languages = new ArrayList<>();
		this.languagesLvl = new ArrayList<>();
	}
	
	String getFirstName() {
		return firstName;
	}
	
	String getLastName() {
		return lastName;
	}
	
	String getEmail() {
		return email;
	}
	
	String getPhone() {
		return phone;
	}
	
	MyDate getBday() {
		return bday;
	}
	
	String getSex()	 {
		return sex;
	}
	
	ArrayList<String> getLanguages() {
		return languages;
	}
	
	ArrayList<String> getLanguagesLvl() {
		return languagesLvl;
	}
	
	void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	void setEmail(String email) {
		this.email = email;
	}
	
	void setPhone(String phone) {
		this.phone = phone;
	}
}
