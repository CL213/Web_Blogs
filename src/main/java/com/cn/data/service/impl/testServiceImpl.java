package com.cn.data.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.data.dao.testDao;
import com.cn.data.service.testService;

@Service
public class testServiceImpl implements testService{
	
	@Autowired
	private testDao testDao;
	
	@Override
	public List<Map<String,Object>> findDataTest(){
		return testDao.findDataTest();
	}
	
}