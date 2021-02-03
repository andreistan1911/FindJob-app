package tema;
public class MyDate implements Comparable<MyDate> {
	//Clasa care imita Date, dar este adaptata la
	//	cerintele aplicatiei noastre.
	int day, month, year;
	
	MyDate() {
		this(0, 0, 0);
	}
	
	
	MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	MyDate(String str) {
		//day.month.year
		String[] split = str.split("\\.", 3); 
		day = Integer.parseInt(split[0]);
		month = Integer.parseInt(split[1]);
		year = Integer.parseInt(split[2]);
	}
	
	public int compareTo(MyDate date) {
		if(year > date.year)
			return 1;
		if(year < date.year)
			return -1;
		if(month > date.month)
			return 1;
		if(month < date.month)
			return -1;
		if(day > date.day)
			return 1;
		if(day < date.day)
			return -1;
		return 0;
	}
	
	public String toString() {
		String sDay = "" + day;
		String sMonth = "" + month;
		if(day < 10)
			sDay = "0" + day;
		if(month < 10)
			sMonth = "0" + month;
		return sDay + "." + sMonth + "." + year;
	}
}
