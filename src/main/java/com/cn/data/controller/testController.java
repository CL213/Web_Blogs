package com.cn.data.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.data.service.testService;

@Controller
@RequestMapping(value = "/test")
public class testController{
	
	@Autowired
	private testService testService;
	
	@RequestMapping(value = "/findData")
	@ResponseBody
	public List<Map<String, Object>> findData(){
		return testService.findDataTest();
	}
	
}