package tema;
import java.util.*;

public class Manager extends Employee{
	ArrayList<Request<Job, Consumer>> requests = new ArrayList<>();
	
	Manager(Information info, ArrayList<Education> edu, ArrayList<Experience> exp, String company, int salary)
			throws ResumeIncompleteException {
		super(info, edu, exp);
		this.company = company;
		this.salary = salary;
	}
	
	public void process(Job job) {
		ArrayList<Request<Job, Consumer>> thisJobReq = new ArrayList<>();
		for(Request<Job, Consumer> request : requests)
			if(request.getKey() == job) 
				thisJobReq.add(request);
		
		Collections.sort(thisJobReq);
		int employed = 0;
		
		for(Request<Job, Consumer> request : thisJobReq) {
			if(employed <= job.noPositions)
				break;
		
			if(request.getValue1().getClass().equals("Employee") == false) {
				employed++;
				User user = (User)request.getValue1();
				user.update(new Notification("", ((Job)request.getKey()).name, company));
				user.convert();
			}
		}
	}
}
