package com.voice.common;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class AppConfig {

	private static final String CONFIG_FILE = "app.properties";

	private static Configuration config;

	private static AppConfig instance = new AppConfig();

	static {
		try {
			config = new PropertiesConfiguration(CONFIG_FILE);
		} catch (ConfigurationException e) {
			throw new IllegalStateException(e);
		}
	}

	public static AppConfig getInstance() {
		return instance;
	}
	
	public String pythonExeFilePath() {
		return config.getString("python.exe.path");
	}

	public String getVoiceFielPath() {
		return config.getString("voice.file.path");
	}
}
