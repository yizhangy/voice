package com.voice.common;

public class AppConfig {

	private static AppConfig instance = new AppConfig();

	public static AppConfig getInstance() {
		return instance;
	}
	
	public String getPythonExeFilePath() {
		return "/Users/yizhangyin/Documents/python-ex/test.py";
	}

	public String getVoiceFieFolderlPath() {
		return "/Users/yizhangyin/Documents/wav";
	}
	
	public String getRecognitionTxtPath() {
		return "/Users/yizhangyin/Documents/recoginition";
	}
}
