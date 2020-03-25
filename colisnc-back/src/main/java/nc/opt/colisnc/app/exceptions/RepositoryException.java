package nc.opt.colisnc.app.exceptions;

/**
 * <p>Exception des dépôts de données</p>
 *
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class RepositoryException extends AbstractGeodeException {

	/** serialVersionUID */
	private static final long serialVersionUID = -8001461012265683026L;
	
	private int status;
	
	public RepositoryException() {
		super();
	}

	public RepositoryException(String message) {
		super(message);
	}
	
	public RepositoryException(int status, String message) {
		super(message);
		this.status = status;
	}

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RepositoryException(String message, Object... strings) {
		super(String.format(message.replace("{}", "%s"), strings));
	}
	
	public RepositoryException(int status, String message, Object... strings) {
		super(String.format(message.replace("{}", "%s"), strings));
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
}
