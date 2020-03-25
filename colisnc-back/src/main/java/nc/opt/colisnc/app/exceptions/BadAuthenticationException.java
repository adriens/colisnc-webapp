package nc.opt.colisnc.app.exceptions;

/**
 * Exception levé lors d'une mauvaise authentification
 * 
 * @author www.redstone.nc
 *
 */
public class BadAuthenticationException extends AbstractGeodeException {

	private static final long serialVersionUID = -3984064630844524714L;

	public BadAuthenticationException() {
		super();
	}

	public BadAuthenticationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadAuthenticationException(String message) {
		super(message);
	}

	public BadAuthenticationException(Throwable cause) {
		super(cause);
	}

}
