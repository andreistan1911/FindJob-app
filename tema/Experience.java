package tema;
public class Experience implements Comparable<Experience>{
	MyDate start, finish;
	String position;
	String company;
	
	Experience(MyDate start, MyDate finish, String position, String company) {
		this.start = start;
		this.finish = finish;
		this.position = position;
		this.company = company;
		try {
			if(finish != null && start.compareTo(finish) == 1)
				throw new InvalidDatesException();
		} catch(InvalidDatesException e){
			System.err.println("finish is before start!!");
		}
	}
	
	public int compareTo(Experience exp) {
		int result = 5;
		
		if(finish == null && exp.finish == null)
			result = start.compareTo(exp.start);
		else if(finish == null)
			result = company.compareTo(exp.company);
		else if(exp.finish == null)
			result = 1;
		
		if(result == 5)//both finish dates are not null
			result = -finish.compareTo(exp.finish);
		
		if(result == 0) {
			result = company.compareTo(exp.company);
		}
		return result;
	}
}
