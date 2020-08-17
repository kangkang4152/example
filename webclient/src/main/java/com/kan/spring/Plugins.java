package com.kan.spring;

import java.util.List;

public class Plugins {
	
private String name;
	
	private List<PluginConfig> configs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PluginConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<PluginConfig> configs) {
		this.configs = configs;
	}

}
