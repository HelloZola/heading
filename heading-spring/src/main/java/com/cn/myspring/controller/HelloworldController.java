package com.cn.myspring.controller;

import example.aspect.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/HelloworldController")
public class HelloworldController{

	@Autowired
	IUserService userService;

	/**
	 * test绫�
	 * http://localhost:8080/heading-spring/HelloworldController/helloworld.do
	 * @return
	 */
	@RequestMapping("/helloworld")
    public ModelAndView hello() {
		System.out.println("fsdfsfsfdfdsf****************************");
		System.out.println("fsdfsfsfdfdsf****************************");
		System.out.println("fsdfsfsfdfdsf****************************");
		System.out.println("fsdfsfsfdfdsf****************************");
		System.out.println("test5");
		userService.addUser();
		ModelAndView model = new ModelAndView();
		model.addObject("fsfsdfdsf");
        return model;
    }
	
}
