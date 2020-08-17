package com.kan.spring;

public class PluginConfig {

	private String id;
	
	private boolean active;
	
	private String className;
	
	private String jarRemoteUrl;
	
	private String name;
	
	private String jarFile;
	
	private String version;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJarRemoteUrl() {
		return jarRemoteUrl;
	}

	public void setJarRemoteUrl(String jarRemoteUrl) {
		this.jarRemoteUrl = jarRemoteUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJarFile() {
		return jarFile;
	}

	public void setJarFile(String jarFile) {
		this.jarFile = jarFile;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
