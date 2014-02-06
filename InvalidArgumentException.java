package proj4;

/**
 * throw exception when the too many or too low argument inserted
 * @author rdg77_000
 *
 */
public class InvalidArgumentException extends Exception {
	public InvalidArgumentException(){
		super("Invalid Argument Exception");
	}
	public InvalidArgumentException(String err){
		super(err);
	}
}
