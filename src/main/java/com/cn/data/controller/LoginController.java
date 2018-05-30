package com.cn.data.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;


@Controller
public class LoginController{
	
	/**
     * 
     * CAS 单点登录
     * @return 跳转的页面
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(WebRequest webRequest, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
    	String userName = request.getRemoteUser();
    	// 有没有登陆用户
    	//System.out.println(userName);
		if (userName != null && !userName.equals("")) {
            return "Index/index";
        } else {
            return "login";
        }
    }
	
}