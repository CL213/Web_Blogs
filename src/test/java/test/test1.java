package test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cn.data.service.testService;

public class test1 extends test{
	
	@Autowired
	private testService testService;
	
	@Test
	public void test1(){
		System.out.println(testService.findDataTest());
	}
	
}