package nc.opt.colisnc.app.providers.openid;

import nc.opt.colisnc.app.providers.AbstractProvider;
import nc.opt.colisnc.app.providers.CodeTypeProvider;

/**
 * <p>Represente un provider OpenId</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class OpenIdProvider extends AbstractProvider {

	public OpenIdProvider() {
		super(CodeTypeProvider.OPEN_ID);
	}

}
