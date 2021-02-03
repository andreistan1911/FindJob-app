package tema;

public class Notification {
	//In notificare este retinut doar un mesaj.
	String msg;
	
	Notification(String s){
		msg = s;
	}
	
	Notification(String s, String jobName, String compName) {
		if(s.equals("new"))
			msg = "Job nou: " + jobName + " in cadrul companiei " + compName + ".";
		else if(s.equals("closed"))
			msg = "Job inchis: " + jobName + " in cadrul companiei " + compName + ".";
		else if(s.equals("rejected"))
			msg = "Respins la jobul " + jobName + " din cadrul companiei " + compName + ".";
		else if(s.equals("hired"))
			msg = "Ai fost angajat la compania " + compName + " pe postul de " + jobName;
	}
}
