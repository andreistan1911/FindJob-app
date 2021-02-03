package tema;
public class Education implements Comparable<Education>{
	MyDate start, finish;
	String name, level;
	double mean; 
	
	Education(MyDate start, MyDate finish, String name, String level, double mean) {
		this.start = start;
		this.finish = finish;
		this.name = name;
		this.level = level;
		this.mean = mean;
		try {
			if(finish != null && start.compareTo(finish) == 1)
				throw new InvalidDatesException();
		} catch(InvalidDatesException e){
			System.err.println("finish is before start!!");
		}
	}
	
	public int compareTo(Education edu) {
		int result = 5;
		
		if(finish == null && edu.finish == null)
			result = start.compareTo(edu.start);
		else if(finish == null)
			result = name.compareTo(edu.name);
		else if(edu.finish == null)
			result = 1;
		
		if(result == 5)//both finish dates are not null
			result = -finish.compareTo(edu.finish);
		
		if(result == 0) {
			if(mean > edu.mean)
				return -1;
			if(mean < edu.mean)
				return 1;
			return 0;
		}
		return result;
	}
}
