package nc.opt.colisnc.app.exceptions;

/**
 * Exception "mère" des excceptions geode
 * 
 * @author www.redstone.nc
 *
 */
public abstract class AbstractGeodeException extends RuntimeException{

	private static final long serialVersionUID = 950720723679765743L;

	public AbstractGeodeException() {
		super();
	}

	public AbstractGeodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AbstractGeodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractGeodeException(String message) {
		super(message);
	}

	public AbstractGeodeException(Throwable cause) {
		super(cause);
	}
	
}
