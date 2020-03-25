package nc.opt.colisnc.app.exceptions;

/**
 * <p>Exception levée par les managers</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class ManagerException extends AbstractGeodeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 2571002243718192453L;

	public ManagerException() {
		super();
	}

	public ManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ManagerException(String message) {
		super(message);
	}

	public ManagerException(Throwable cause) {
		super(cause);
	}
	
	public ManagerException(String message, Object... strings) {
		super(String.format(message.replace("{}", "%s"), strings));		
	}
}
