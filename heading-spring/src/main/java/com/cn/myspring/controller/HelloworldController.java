package com.cn.myspring.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import example.aspect.IUserService;
import example.aspect.UserServiceImpl;

@Controller
@RequestMapping("/HelloworldController")
public class HelloworldController{
	
	/**
	 * test绫�
	 * http://localhost:8080/heading-spring/HelloworldController/helloworld.do
	 * 
	 * @return
	 */
	@RequestMapping("/helloworld")
    public ModelAndView hello() {
		
		System.out.println("fsdfsfsfdfdsf****************************");
		System.out.println("fsdfsfsfdfdsf****************************");
		System.out.println("fsdfsfsfdfdsf****************************");
		System.out.println("fsdfsfsfdfdsf****************************");
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/contextLoaderListener.xml");
		UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
		userService.addUser();
		
		ModelAndView model = new ModelAndView();
		model.addObject("fsfsdfdsf");
        return model;
    }
	
}
