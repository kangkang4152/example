package com.kan.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kan.service.factory.PluginFactory;
import com.kan.spring.PluginConfig;

@Controller
@RequestMapping(value="/plugin")
public class PluginController {
	
	@Autowired
	private PluginFactory pluginFactory;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse response) {
		Collection<PluginConfig> list = null;
		try {
			list = pluginFactory.flushConfigs();
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("plugins", list);
		return "/index";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("test", "test");
		return "/login";
	}
	
	@RequestMapping(value="/active",method = RequestMethod.GET)
	public void activePlugin(HttpServletRequest request,HttpServletResponse response) {
		pluginFactory.activePlugin("1");
	}

	
	@RequestMapping(value="/disable",method = RequestMethod.GET)
	public void disablePlugin(HttpServletRequest request,HttpServletResponse response) {
		pluginFactory.disablePlugin("1");
	}

}
