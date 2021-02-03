package tema;
import java.lang.Math;
import java.util.ArrayList;

public class Recruiter extends Employee {
	static Double rating = 5.0;
	
	Recruiter(Information info, ArrayList<Education> edu, ArrayList<Experience> exp, int salary) throws ResumeIncompleteException {
		super(info, edu, exp, salary);
	}
	
	public int evaluate(Job job, User user) {
		int score = (int)Math.ceil(user.getTotalScore() * rating);
		if(job.flag == false) 
			score = 0;
		rating += 0.1;
		Application.getInstance().getCompany(resume.exp.get(resume.exp.size() - 1).company).
				manager.requests.add(new Request<Job, Consumer>(job, (Consumer)user, (Consumer)this, score * (rating - 0.1)));
		
		return score * (int)(rating - 0.1);
	}
}
