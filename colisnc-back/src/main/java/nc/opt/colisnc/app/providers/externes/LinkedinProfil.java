package nc.opt.colisnc.app.providers.externes;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface LinkedinProfil {
	@JsonProperty(value = "emailAddress") String getEmail();
	@JsonProperty(value = "id") String getSub();
	@JsonProperty(value = "formattedName") String getName();
}
