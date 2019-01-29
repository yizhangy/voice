package com.voice.module;

import com.google.inject.servlet.ServletModule;

public class WebModule extends ServletModule {
	
	@Override
	protected void configureServlets() {
		install(new BusinessServiceModule());
	}

}
