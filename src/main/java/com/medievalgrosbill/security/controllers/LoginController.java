package com.medievalgrosbill.security.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.medievalgrosbill.security.CustomAuthenticationSuccessHandler;

@Controller
public class LoginController {
	
	public static final String FORM_PASSWORD = "password";
	public static final String FORM_USERNAME = "username";
	private static final String SECURITY_LOGIN = "/security/login";
	public static final String LOGIN = "/login";

	
//	public String index(Model model, HttpServletRequest request) {
//		
//		HttpSession session = request.getSession(false);
//		session.setAttribute("redirection", request.getRequestURI());
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getAttribute("redirection"));
//		System.out.println(request.getQueryString());
//		
//		return SECURITY_LOGIN;
//	}
	
	@RequestMapping(value={LOGIN}, method=RequestMethod.GET)
	public String login(Model model, Principal principal, HttpServletRequest request) throws Exception{
		model.addAttribute("form_username",FORM_USERNAME);
		model.addAttribute("form_password",FORM_PASSWORD);
		String referer = request.getHeader("Referer"); //Get previous URL before call '/login'
        request.getSession().setAttribute(CustomAuthenticationSuccessHandler.REDIRECT_URL_SESSION_ATTRIBUTE_NAME, referer); 
		// isConnected ?
		model.addAttribute("isConnected", false);
		model.addAttribute("isAdmin", false);
		// isOnLogin
		model.addAttribute("isOnLogin", true);
		// isOnRegister
		model.addAttribute("isOnRegister", false);
        return principal == null ?  SECURITY_LOGIN : "redirect:/"; 
    }
}
