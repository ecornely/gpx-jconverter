package be.ecornely.gpx.configuration;

import java.io.Serializable;
import java.util.List;

/**
 * Interface to represent all the configurable items in gpx-jconverter
 */
public interface Configuration extends Serializable{
	
	/**
	 * Simple key/value pair to represent cookies
	 * */
	public static class Cookie implements Serializable{
		private static final long serialVersionUID = 1L;
		private String key;
		private String value;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public Cookie() {
		}
		public Cookie(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}
		@Override
		public String toString() {
			return "Cookie [key=" + key + ", value=" + value + "]";
		}
		
		
	}

	/**
	 * Set a list of cookies
	 */
	void setCookies(List<Cookie> cookies);
	
	/**
	 * Get the list of cookies
	 * */
	List<Cookie> getCookies();
}
