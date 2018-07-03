package com.livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class Home {

	@RequestMapping(value = "/sobre")
	public ModelAndView sobre() {
		return new ModelAndView("sobre");
	}
	
	@RequestMapping()
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	
}
