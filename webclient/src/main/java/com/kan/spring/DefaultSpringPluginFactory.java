package com.kan.spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class DefaultSpringPluginFactory implements ApplicationContextAware {

	
	private static final String configPath = "F:/document/plugins.txt";
	
	private ApplicationContext applicationContext;
	private Map<String,PluginConfig> configs = new HashMap<String,PluginConfig>();
	private Map<String,Advice> adviceCache = new HashMap<>();
	
	
	public void activePlugin(String pluginId) {
		if(!configs.containsKey(pluginId)) {
			throw new RuntimeException(String.format("指定的插件不存在  id=%s", pluginId));
		}
		
		PluginConfig config = configs.get(pluginId);
		
		config.setActive(true);
		
		for(String name : applicationContext.getBeanDefinitionNames()) {
			Object bean = applicationContext.getBean(name);
			if(bean == this) {
				continue;
			}
			if(!(bean instanceof Advised)) {
				continue;
			}
			if(findAdvice((Advised) bean,config.getClass().getName()) != null) {
				continue;
			}
			
			Advice advice = null;
			try {
				advice = buildAdvice(config);
				((Advised)bean).addAdvice(advice);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private Advice buildAdvice(PluginConfig config)  {
		if(adviceCache.containsKey(config.getClassName())) {
			return adviceCache.get(config.getClassName());
		}
		//获取本地加载的jar包
		try {
			URL targetUrl = new URL(config.getJarRemoteUrl());
			URLClassLoader loader = (URLClassLoader)getClass().getClassLoader();
			boolean isloader = false;
			for(URL url : loader.getURLs()) {
				if(url.equals(targetUrl)) {
					isloader = true;
					break;
				}
			}
			if(!isloader) {
				Method add = URLClassLoader.class.getDeclaredMethod("addURL",new Class[] {URL.class});
				add.setAccessible(true);
				add.invoke(loader, targetUrl);
			}
			
			Class<?> adviceClass = loader.loadClass(config.getClassName());
			adviceCache.put(adviceClass.getName(), (Advice)adviceClass.newInstance());
			
			return adviceCache.get(adviceClass.getName());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}catch ( SecurityException e) {
			e.printStackTrace();
		}catch ( IllegalAccessException  e) {
			e.printStackTrace();
		}catch ( IllegalArgumentException  e) {
			e.printStackTrace();
		}catch ( InvocationTargetException e) {
			e.printStackTrace();
		}catch ( ClassNotFoundException e) {
		e.printStackTrace();
	} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Advice findAdvice(Advised advised,String className) {
		for(Advisor a : advised.getAdvisors()) {
			if(a.getAdvice().getClass().getName().equals(className)) {
				return a.getAdvice();
			}
		}
		return null;
	}

	public Collection<PluginConfig> flushConfigs() throws IOException{
		
		//用文件读 产生乱码  读文件的时候要用utf-8
		File configFile = new File(configPath);

		InputStreamReader inputReader = new InputStreamReader(new FileInputStream(configFile),"utf-8");
		BufferedReader reader = new BufferedReader(inputReader);
		
		String tempStr;
		StringBuffer configJson = new StringBuffer();
		while((tempStr = reader.readLine()) != null) {
			configJson.append(tempStr);
		}
		
		inputReader.close();
		reader.close();
		
		
		Plugins plugionConfigs = JSON.parseObject(configJson.toString(), Plugins.class);
		
		for(PluginConfig plugionConfig : plugionConfigs.getConfigs()) {
			if(configs.get(plugionConfig.getId()) == null){
				configs.put(plugionConfig.getId(), plugionConfig);
			}
		}
		
		return configs.values();
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	
	public void disablePlugin(String pluginId) {
		if(!configs.containsKey(pluginId)) {
			throw new RuntimeException(String.format("指定的插件不存在，id=%s", pluginId));
		}
		PluginConfig config = configs.get(pluginId);
		config.setActive(false);
		for(String name : applicationContext.getBeanDefinitionNames()) {
			Object bean = applicationContext.getBean(name);
			if(bean instanceof Advised) {
				Advice advice =  findAdvice((Advised) bean,config.getClassName());
				if(advice != null )
					((Advised)bean).removeAdvice(advice);
			}
		}
	}
}
