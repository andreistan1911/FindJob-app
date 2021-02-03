 package tema;

import java.util.*;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
	
	static Information info;
    static int salary;
    static String[] names;
    static ArrayList<String> languages;
    static ArrayList<String> languagesLvl;
    static ArrayList<String> interestedCompanies;
    static ArrayList<Education> edu;
    static ArrayList<Experience> exp;
    static String depName;
    
    void read(JSONObject o) {
    		//Citirea unui element din JSON
    		languages = new ArrayList<>();
    		languagesLvl = new ArrayList<>();
    		interestedCompanies = new ArrayList<>();
    		edu = new ArrayList<>();
    		exp = new ArrayList<>();
    		
    		String name = o.getString("name");
    		String email = o.getString("email");
    		String phone = o.getString("phone");
    		MyDate bday = new MyDate(o.getString("date_of_birth"));
    		String genre = o.getString("genre");
    		JSONArray JSONlanguages = o.getJSONArray("languages");
    		JSONArray JSONlanguages_lvl = o.getJSONArray("languages_level");
    		JSONArray JSONinterested_companies;
    		if(o.has("interested_companies")) {
    			JSONinterested_companies = o.getJSONArray("interested_companies");
    			for(int j = 0; j < JSONinterested_companies.length(); j++)
        			interestedCompanies.add(JSONinterested_companies.getString(j));
    		}
    		if(o.has("salary"))
    			salary = o.getInt("salary");
    		JSONArray JSONeducation = o.getJSONArray("education");
    		JSONArray JSONexperience = o.getJSONArray("experience");
    		
    		names = name.split(" ", 4); 
        	info = new Information(names[0], names[1], email, phone, genre, bday, languages, languagesLvl);
    		
    		for(int j = 0; j < JSONeducation.length(); j++) {
    			String eduLevel = JSONeducation.getJSONObject(j).getString("level");
    			String eduName = JSONeducation.getJSONObject(j).getString("name");
    			MyDate eduStart = new MyDate(JSONeducation.getJSONObject(j).getString("start_date"));
    			MyDate eduEnd = null;
    			if(!JSONeducation.getJSONObject(j).isNull("end_date"))
    				eduEnd = new MyDate(JSONeducation.getJSONObject(j).getString("end_date"));
    			double eduGrade = JSONeducation.getJSONObject(j).getDouble("grade");
    			edu.add(new Education(eduStart, eduEnd, eduName, eduLevel, eduGrade));
    		}
    	
    		for(int j = 0; j < JSONexperience.length(); j++) {
    			String expCompany = JSONexperience.getJSONObject(j).getString("company");
    			String expPosition = JSONexperience.getJSONObject(j).getString("position");
    			if(JSONexperience.getJSONObject(j).has("department")) 
    				depName = JSONexperience.getJSONObject(j).getString("department");
    			MyDate expStart = new MyDate(JSONexperience.getJSONObject(j).getString("start_date"));
    			MyDate expEnd = null;
    			if(!JSONexperience.getJSONObject(j).isNull("end_date"))
    				expEnd = new MyDate(JSONexperience.getJSONObject(j).getString("end_date"));
    			exp.add(new Experience(expStart, expEnd, expPosition, expCompany));
    		}
        
    		for(int j = 0; j < JSONlanguages.length(); j++) {
    			languages.add(JSONlanguages.getString(j));
    			languagesLvl.add(JSONlanguages_lvl.getString(j));
    		}
    }
    
	public static void main(String args[]) {
		Application app = Application.getInstance();
		Test t = new Test();
		Scanner sc;
		String line;
		String[] lineSplit;
		
		try {
			//Citirea job-urilor nedisponibile 
			sc = new Scanner(new File("companies.txt"));
			line = sc.nextLine();
			lineSplit = line.split(",");
			for(String s : lineSplit) 
				app.add(new Company(s));
			
			sc = new Scanner(new File("jobs.txt"));
			
			Department depIT = new IT();
			Department depMan = new Management();
			Department depMark = new Marketing();
			Department depFin = new Finance();
			String compName = null;
			
			while(sc.hasNextLine()) {
				
				line = sc.nextLine();
				lineSplit = line.split(",|\\:|-");

				if(lineSplit[0].charAt(0) == '.') {
					if(compName != null) {
						app.getCompany(compName).add(depIT);
						app.getCompany(compName).add(depMan);
						app.getCompany(compName).add(depMark);
						app.getCompany(compName).add(depFin);
						depIT = new IT();
						depMan = new Management();
						depMark = new Marketing();
						depFin = new Finance();
					}
					compName = lineSplit[0].split("\\.")[1];
					if(compName.equals("Amazon"))
						depIT.add(new Job("Recruiter", compName, 5000));
					if(compName.equals("Google"))
						depIT.add(new Job("Recruiter", compName, 8000));
				}
				
				Job job = null;
				
				if(lineSplit[0].equals("IT"))
					for(int i = 1; i < lineSplit.length; i += 2) {
						job = new Job(lineSplit[i], compName, Integer.parseInt(lineSplit[i + 1]));
						
						if(!depIT.contains(job.name))
							depIT.add(job);
					}
				if(lineSplit[0].equals("Management"))
					for(int i = 1; i < lineSplit.length; i += 2) {
						job = new Job(lineSplit[i], compName, Integer.parseInt(lineSplit[i + 1]));
						
						if(!depMan.contains(job.name))
							depMan.add(job);
					}
				if(lineSplit[0].equals("Marketing"))
					for(int i = 1; i < lineSplit.length; i += 2) {
						job = new Job(lineSplit[i], compName, Integer.parseInt(lineSplit[i + 1]));
						
						if(!depMark.contains(job.name))
							depMark.add(job);
					}
				if(lineSplit[0].equals("Finance"))
					for(int i = 1; i < lineSplit.length; i += 2) {
						job = new Job(lineSplit[i], compName, Integer.parseInt(lineSplit[i + 1]));
						
						if(!depFin.contains(job.name))
							depFin.add(job);
					}
				
			}
			
			app.getCompany(compName).add(depIT);
			app.getCompany(compName).add(depMan);
			app.getCompany(compName).add(depMark);
			app.getCompany(compName).add(depFin);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			//Citirea job-urilor disponibile 
			
			sc = new Scanner(new File("availablejobs.txt"));
			
			String compName = null;
			
			while(sc.hasNextLine()) {
				
				line = sc.nextLine();
				lineSplit = line.split(",");

				if(lineSplit[0].charAt(0) == '.') {
					compName = lineSplit[0].split("\\.")[1];
					continue;
				}
				
				app.getCompany(compName).noAvailableJobs++; 
				String name = lineSplit[0];
				int noPositions = Integer.parseInt(lineSplit[1]);
				int salary = Integer.parseInt(lineSplit[2]);
				Integer gycLow, gycUp, eycLow, eycUp;
				Double mcLow, mcUp;
				
				if(lineSplit[3].equals("null"))	gycLow = null;
				else gycLow = Integer.parseInt(lineSplit[3]);
				if(lineSplit[4].equals("null")) gycUp = null;
				else gycUp = Integer.parseInt(lineSplit[4]);
				if(lineSplit[5].equals("null"))	eycLow = null;
				else eycLow = Integer.parseInt(lineSplit[5]);
				if(lineSplit[6].equals("null")) eycUp = null;
				else eycUp = Integer.parseInt(lineSplit[6]);
				if(lineSplit[7].equals("null"))	mcLow = null;
				else mcLow = Double.parseDouble(lineSplit[7]);
				if(lineSplit[8].equals("null")) mcUp = null;
				else mcUp = Double.parseDouble(lineSplit[8]);
			
				boolean found = false;
				for(int i = 0; i < app.getCompany(compName).getDepartments().size(); i++) {
					for(int j = 0; j < app.getCompany(compName).getDepartments().get(i).getAllJobs().size(); j++) {
						if(app.getCompany(compName).getDepartments().get(i).getAllJobs().get(j).name.equals(lineSplit[0])) {
							app.getCompany(compName).getDepartments().get(i).getAllJobs().set(j,
									new Job(name, compName, salary, noPositions,
											gycLow, gycUp, eycLow, eycUp, mcLow, mcUp)); 
							i = app.getCompany(compName).getDepartments().size();
							found = true;
							break;
						}
					}
				}
				if(!found)
					app.getCompany(compName).getDepartment("IT").add(new Job(name, compName, salary, noPositions,
							gycLow, gycUp, eycLow, eycUp, mcLow, mcUp));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			//Citirea detalilor despre consumatori
			String fname = "consumers.json";
            String contents = new String((Files.readAllBytes(Paths.get(fname))));
            JSONObject obj = new JSONObject(contents);

            JSONArray employees = obj.getJSONArray("employees");
            JSONArray recruiters = obj.getJSONArray("recruiters");
            JSONArray users = obj.getJSONArray("users");
            JSONArray managers = obj.getJSONArray("managers");
           
            
            //Intai parcurg managerii pentru a determina companiile 
            for(int i = 0; i < managers.length(); i++) {
            	t.read(managers.getJSONObject(i));
            	String companyName = exp.get(exp.size() - 1).company;
            	
            	//Adaug in aplicatie compania curenta cu managerul citit
            	try {
            		app.getCompany(companyName).manager = new Manager(info, edu, exp, companyName, salary);
            	} catch (ResumeIncompleteException e) {
            		e.printStackTrace();
            	}
            }
            
            for(int i = 0; i < employees.length(); i++) { 
            	t.read(employees.getJSONObject(i));
            	String crrCompName = exp.get(exp.size() - 1).company;
            	try {
            		app.getCompany(crrCompName).noEmployees++;
            		app.getCompany(crrCompName).add(new Employee(info, edu, exp, salary), app.getCompany(crrCompName).getDepartment(depName));
            	} catch (ResumeIncompleteException e) {
            		e.printStackTrace();
            	}
            }
            
            for(int i = 0; i < recruiters.length(); i++) {
            	t.read(recruiters.getJSONObject(i));
            	String crrCompName = exp.get(exp.size() - 1).company;
            	try {
            		app.getCompany(crrCompName).add(new Recruiter(info, edu, exp, salary), app.getCompany(crrCompName).getDepartment("IT"));
            		app.getCompany(crrCompName).add(new Recruiter(info, edu, exp, salary));
            	} catch (ResumeIncompleteException e) {
            		e.printStackTrace();
            	}
            }
            
            for(int i = 0; i < users.length(); i++) {
            	t.read(users.getJSONObject(i));
            	try {
            		User newUser = new User(info, edu, exp);
            		newUser.addInterestedCompanies(interestedCompanies);
            		app.add(newUser);
            	} catch (ResumeIncompleteException e) {
            		e.printStackTrace();
            	}
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        }
		
		//Am adaugat un bloc aici pentru organizarea codului
		{
			//HardCoding pe reteaua sociala
			app.getUsers().get(0).add(app.getUsers().get(1));
			app.getUsers().get(0).add(app.getCompany("Amazon").getEmployees().get(2));
			app.getUsers().get(1).add(app.getUsers().get(0));
			app.getUsers().get(1).add(app.getCompany("Google").getEmployees().get(1));
			app.getUsers().get(1).add(app.getCompany("Google").getRecruiters().get(0));
			app.getUsers().get(2).add(app.getUsers().get(3));
			app.getUsers().get(2).add(app.getCompany("Amazon").getEmployees().get(2));
			app.getUsers().get(3).add(app.getUsers().get(2));
			app.getUsers().get(3).add(app.getCompany("Google").getEmployees().get(4));

	        app.getCompany("Amazon").getEmployees().get(1).add(app.getCompany("Google").getEmployees().get(4));
	        app.getCompany("Amazon").getEmployees().get(1).add(app.getCompany("Amazon").getRecruiters().get(0));
	        app.getCompany("Amazon").getEmployees().get(2).add(app.getUsers().get(0));
	        app.getCompany("Amazon").getEmployees().get(2).add(app.getUsers().get(2));
	        app.getCompany("Amazon").getEmployees().get(2).add(app.getCompany("Google").getEmployees().get(0));
	        app.getCompany("Amazon").getEmployees().get(2).add(app.getCompany("Google").getRecruiters().get(1));
	        app.getCompany("Google").getEmployees().get(0).add(app.getCompany("Amazon").getEmployees().get(2));
	        app.getCompany("Google").getEmployees().get(0).add(app.getCompany("Amazon").getRecruiters().get(1));
	        app.getCompany("Google").getEmployees().get(4).add(app.getUsers().get(3));
	        app.getCompany("Google").getEmployees().get(4).add(app.getCompany("Amazon").getEmployees().get(1));
	        app.getCompany("Google").getEmployees().get(1).add(app.getUsers().get(1));

	        app.getCompany("Google").getRecruiters().get(0).add(app.getUsers().get(1));
	        app.getCompany("Google").getRecruiters().get(1).add(app.getCompany("Amazon").getEmployees().get(2));
	        app.getCompany("Amazon").getRecruiters().get(0).add(app.getCompany("Amazon").getEmployees().get(1));
	        app.getCompany("Amazon").getRecruiters().get(1).add(app.getCompany("Google").getEmployees().get(0));		
	    }
		
		for(int i = 0; i < app.getCompanies().size(); i++)
         	for(User user : app.getUsers())  
         		if(user.interestedCompanies.contains(app.getCompanies().get(i).name)) {
         			//User-ul curent este interesat
         			Recruiter rec = app.getCompanies().get(i).getRecruiter(user);
         			for(Department dep : app.getCompanies().get(i).getDepartments())
         				for(Job job : dep.getJobs()) { 
         					if(job.flag == true) {
         						rec.evaluate(job, user);
         					}
         					app.getCompanies().get(i).manager.process(job);
         				}
         		}
		
		 Page.app = app;
         Page.setFrame();
		 GraphicInterface.display();
	}
}
