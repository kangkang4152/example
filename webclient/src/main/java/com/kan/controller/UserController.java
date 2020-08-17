package com.kan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kan.service.UserService;

@Controller
@RequestMapping(value="/example")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse response) {
		userService.testWithOutTranstaction();
		return "/index";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response) {
		userService.list();
		return "/login";
	}
	
	
}
