package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cn.redis.Redis_MiaoSha;

public class JedisTest1 {
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10000; i++){
            executor.execute(new Redis_MiaoSha("user"+i, "iphone8Num"));
        }
        executor.shutdown();
	}
	
}