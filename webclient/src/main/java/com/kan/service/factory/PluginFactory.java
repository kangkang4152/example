package com.kan.service.factory;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kan.spring.DefaultSpringPluginFactory;
import com.kan.spring.PluginConfig;
	
	
@Service
public class PluginFactory {
	
	@Autowired
	private DefaultSpringPluginFactory pluginFactory;

	public Collection<PluginConfig> flushConfigs() {
		
		try {
			return pluginFactory.flushConfigs();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean activePlugin(String pluginId) {
		try {
			 pluginFactory.activePlugin(pluginId);;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public boolean disablePlugin(String pluginId) {
		try {
			 pluginFactory.disablePlugin(pluginId);;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
