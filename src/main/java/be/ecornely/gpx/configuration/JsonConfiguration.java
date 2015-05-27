package be.ecornely.gpx.configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Default implementation of the gpx-jconverter {@link Configuration}</p>
 * <p>This implementation is a Singleton reading a file under the current execution path named config.json</p>
 * */
public class JsonConfiguration implements Configuration {
	private static final long serialVersionUID = 1L;
	private static boolean creation = false;
	
	private List<Cookie> cookies;
	
	private String username;
	
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		this.saveToFile();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.saveToFile();
	}


	private ObjectMapper objectMapper = new ObjectMapper();
	
	private File configFile;

	public JsonConfiguration(File configFile) {
		this.configFile = configFile;
		try {
			BeanUtils.copyProperties(this, this.readFromFile());
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).warn("Unable to copy properties loaded from file "+this.configFile.getAbsolutePath(), e);
		}
	}
	
	private JsonConfiguration() {
	}
	
	@Override
	public List<Cookie> getCookies() {
		return cookies;
	}

	@Override
	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
		this.saveToFile();
	}

	private void saveToFile() {
		if(!creation){
			if(!configFile.exists() || configFile.canWrite()){
				try {
					objectMapper.writerFor(JsonConfiguration.class).writeValue(configFile, this);
				} catch (IOException e) {
					LoggerFactory.getLogger(this.getClass()).warn("Cannot save the configuration as json in "+configFile.getAbsolutePath(), e);
				}
			}
		}
	}


	private JsonConfiguration readFromFile(){
		JsonConfiguration configuration = new JsonConfiguration();
		if(configFile.exists() && configFile.canRead()){
			try {
				creation = true;
				configuration = objectMapper.reader(JsonConfiguration.class).readValue(configFile);
			} catch (IOException e) {
				LoggerFactory.getLogger(JsonConfiguration.class).warn("Cannot save the configuration as json in "+configFile.getAbsolutePath(), e);
			}finally {
				creation = false;
			}
		}else{
			LoggerFactory.getLogger(JsonConfiguration.class).info("Cannot read the configuration :"+configFile.getAbsolutePath());
		}
		return configuration;
	} 
}
