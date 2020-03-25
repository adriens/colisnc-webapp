package nc.opt.colisnc.app.exceptions;

/**
 * <p>Exception reservé pour la gestion des Providers</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class ProviderException extends AbstractGeodeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1083980896393410023L;

	public ProviderException() {
		super();
	}

	public ProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProviderException(String message) {
		super(message);
	}

	public ProviderException(Throwable cause) {
		super(cause);
	}
	
	public ProviderException(String message, Object... strings) {
		super(String.format(message.replace("{}", "%s"), strings));
	}
	
}
