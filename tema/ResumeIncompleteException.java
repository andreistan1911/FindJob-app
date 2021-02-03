package tema;

public class ResumeIncompleteException extends Exception{
	ResumeIncompleteException() {
		System.err.println("Resume is not complete");
	}
	ResumeIncompleteException(String msg) {
		super(msg);
	}
}
