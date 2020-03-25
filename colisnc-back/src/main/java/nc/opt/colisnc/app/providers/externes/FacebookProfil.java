package nc.opt.colisnc.app.providers.externes;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface FacebookProfil {
 	@JsonProperty(value = "id") String getSub();
}
