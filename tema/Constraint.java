package tema;
class Constraint<T extends Comparable<T>>{
	private T lowerBound, upperBound;
	
	Constraint(T lowerBound, T upperBound) { 
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	T getLowerBound() {
		return lowerBound;
	}
	
	T getUpperBound() {
		return upperBound;
	}
	
	boolean check(T value) {
		boolean lowChecked = false;
		boolean upChecked = false;
		
		if(lowerBound == null || lowerBound.compareTo(value) <= 0)
			lowChecked = true;
		if(upperBound == null || value.compareTo(upperBound) <= 0)
			upChecked =  true;
		
		if(lowChecked && upChecked) 
			return true;
		return false;
	}
}
