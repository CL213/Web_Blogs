package com.cn.data.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.data.service.testService;

@Controller
@RequestMapping(value = "/test")
@ResponseBody
public class testController{
	
	@Autowired
	private testService testService;
	
	//现金分析
	@RequestMapping(value = "/findData")
	public List<Map<String, Object>> findData(Model modal){
		return testService.findDataTest();
	}
	
}