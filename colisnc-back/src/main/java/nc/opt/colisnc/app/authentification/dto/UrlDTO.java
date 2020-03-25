package nc.opt.colisnc.app.authentification.dto;

/**
 * <p>DTO pour encapsuler une url</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class UrlDTO {

	private String url;

	public UrlDTO(String url) {
		this.url = url;
	}

	/** @return l'url */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UrlConnection [url=" + url + "]";
	}
}
