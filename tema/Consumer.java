package tema;
import java.time.LocalDate;
import java.util.*;

public abstract class Consumer {
	static class Resume {
		Information info;
		ArrayList<Education> edu = new ArrayList<>();
		ArrayList<Experience> exp = new ArrayList<>();
		
		private Resume(ResumeBuilder builder) throws ResumeIncompleteException {
			if(builder.edu.isEmpty() || builder.exp.isEmpty())
	 			throw new ResumeIncompleteException();
	 		info = builder.info;
			edu = builder.edu;
			exp = builder.exp;
		}
		
		public static class ResumeBuilder {
			private Information info;
			private ArrayList<Education> edu = new ArrayList<>();
			private ArrayList<Experience> exp = new ArrayList<>();
			
			public ResumeBuilder(Information info) {
				this.info = info;
			}
			
			public ResumeBuilder edu(ArrayList<Education> edu) {
				this.edu = edu;
				return this;
			}
			
			public ResumeBuilder exp(ArrayList<Experience> exp) {
				this.exp = exp;
				return this;
			}
			
			public Resume build() {
				Resume res;
				try {
					res = new Resume(this);
				} catch(ResumeIncompleteException e) {
					return null;
				}
				return res;
			}
		}
	}
	
	int level = 0;
	Resume resume;
 	ArrayList<Consumer> acquaintances = new ArrayList<>();
 	
 	Consumer() {
 		//oop
 	}
 	
 	Consumer(Information info, ArrayList<Education> edu, ArrayList<Experience> exp) throws ResumeIncompleteException {
 		if(edu.isEmpty() || exp.isEmpty())
 			throw new ResumeIncompleteException();
 		resume = new Resume.ResumeBuilder(info)
 				.edu(edu)
 				.exp(exp)
 				.build();
 	}
 	
	public void add(Education education) {
		resume.edu.add(education);
	}
	 
	public void add(Experience experience) {
		resume.exp.add(experience);
	}
	
	public void add(Consumer consumer) { 
		acquaintances.add(consumer);
	}

	public int getDegreeInFriendship(Consumer consumer) {
		if(acquaintances.contains(consumer))
			return 1;
		ArrayList<Consumer> visited = new ArrayList<>();
		LinkedList<Consumer> queue = new LinkedList<>();
		visited.add(this);
		queue.add(this);
		
		while(!queue.isEmpty()) {
			Consumer crr = queue.poll();
			if(crr.toString().equals(consumer.toString())) {
				int mustReturn = crr.level;
				Application.getInstance().resetLevels();
				return mustReturn;
			}
				
			for(Consumer next : crr.acquaintances) {
				if(!visited.contains(next)) {
					next.level = crr.level + 1;
					visited.add(next);
					queue.add(next);
				}
			}
		}
		Application.getInstance().resetLevels();
		return -1;
	}
	
	public void remove(Consumer consumer) {
		acquaintances.remove(consumer);
	}
	
	public Integer getGraduationYear() {
		for(Education edu : resume.edu) 
			if(edu.level.equals("college") && edu.finish != null) 
				return edu.finish.year;
		return null;
	}
	
	public int getExperienceYears() {
		if(resume.exp.get(resume.exp.size() - 1).finish != null)
			return 1 + resume.exp.get(resume.exp.size() - 1).finish.year - resume.edu.get(0).start.year;

		String[] split = LocalDate.now().toString().split("-");
		MyDate today = new MyDate(Integer.parseInt(split[1]),
									Integer.parseInt(split[2]),
									Integer.parseInt(split[0]));
		return 1 + today.year - resume.edu.get(0).start.year;
	}
	
	public Double meanGPA() {
		double mean = 0;
		for(Education edu : resume.edu)
			mean += edu.mean;
		mean /= resume.edu.size();
		return mean;
	}
	
	public String toString() {
		return resume.info.getFirstName() + " " + resume.info.getLastName();
	}
}
