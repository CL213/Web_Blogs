package hadoop;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.hadoop.HDFS.HDFSOperate;


@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({ "classpath:mybatis/spring-mybatis-oracle.xml", "classpath:spring/spring-service.xml", "classpath:redis/spring-redis.xml", "classpath:hadoop/spring-hadoop.xml" })
public class HadoopTest {
	
	@Autowired
	private HDFSOperate HDFSOperate;
	
	@Test
    public void test(){
		try {
			HDFSOperate.deleteAndCreateNewFile();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}