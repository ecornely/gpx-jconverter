package be.ecornely.gpx.configuration;

import java.io.File;
import java.util.List;

/**
 * <p>Default implementation of the gpx-jconverter {@link Configuration}</p>
 * <p>This implementation is a Singleton reading a file under the current execution path named config.json</p>
 * */
public class DefaultConfiguration implements Configuration {
	private static Configuration INSTANCE = null;
	private static final long serialVersionUID = 1L;

	private DefaultConfiguration() {
	}
	
	public static Configuration getInstance() {
		if(INSTANCE == null){
			// TODO Load it from ~/.gpx-jconverter/config.json
			INSTANCE = new JsonConfiguration(new File("config.json"));
		}
		return INSTANCE;
	}

	public void setCookies(List<Cookie> cookies) {
		INSTANCE.setCookies(cookies);
	}

	public List<Cookie> getCookies() {
		return INSTANCE.getCookies();
	}

	
	
}
