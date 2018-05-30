package test;

import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:mybatis/spring-mybatis-oracle.xml", "classpath:spring/spring-service.xml", "classpath:redis/spring-redis.xml" })
public class test {

}