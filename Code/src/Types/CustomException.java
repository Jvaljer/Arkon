package Types;

/**
 * this class is meant to make custom error messages throughout my code
 * @author abel
 *
 */
public class CustomException extends Exception{
	public CustomException(String msg) {
		super(msg);
	}
}
