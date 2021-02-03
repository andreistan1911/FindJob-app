package tema;
public class InvalidDatesException extends Exception {
	InvalidDatesException() {
		System.err.println("Invalid dates");
	}
	InvalidDatesException(String msg) {
		super(msg);
	}
}