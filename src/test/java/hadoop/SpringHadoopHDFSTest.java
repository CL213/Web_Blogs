package hadoop;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @program: hadoop
 * @description: 操作测试-使用Spring Hadoop来访问操作HDFS分布式文件系统
 * @author: skyofyuan
 * @create: 2018-06-19
 **/
public class SpringHadoopHDFSTest {

    private ApplicationContext ctx;
    private FileSystem HDFSFileSystem;

    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("classpath:mybatis/spring-mybatis-oracle.xml", "classpath:spring/spring-service.xml", "classpath:redis/spring-redis.xml", "classpath:hadoop/spring-hadoop.xml");
        HDFSFileSystem = (FileSystem) ctx.getBean("HDFSFileSystem");
    }

    @After
    public void tearDown() throws IOException {
        ctx = null;
        HDFSFileSystem.close();
    }

    //测试1
    /**
     * 在HDFS上创建一个目录/Skyofyuan
     * @throws Exception
     */
//    @Test
//    public void testMkdirs()throws Exception{
//    	HDFSFileSystem.mkdirs(new Path("/Skyofyuan/"));
//    }
    
    //测试2
    /**
     * 读取HDFS上/output/part-r-00000文件内容
     * @throws Exception
     */
    @Test
    public void testText()throws Exception{
        FSDataInputStream in = HDFSFileSystem.open(new Path("/output/part-r-00000"));
        IOUtils.copyBytes(in, System.out, 1024);
        in.close();
    }
    
}