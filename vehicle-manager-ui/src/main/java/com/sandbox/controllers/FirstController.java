package com.sandbox.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sandbox.constants.CommonConstants;

@Controller
public class FirstController {

	@RequestMapping(CommonConstants.SLASH+CommonConstants.HOME_HTML)
	public ModelAndView firstPage()
	{
		Date dateAndTime = new Date();	
		return new ModelAndView("welcome", "dateAndTime", dateAndTime);
	}
}
