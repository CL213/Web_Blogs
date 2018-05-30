package test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

import com.cn.data.service.testService;
import com.cn.redis.RedisClientTemplate;
import com.cn.redis.Redis_MiaoSha;

public class test1 extends test{
	
	@Autowired
	private testService testService;
	
	@Autowired
    private RedisClientTemplate redisClientTemplate;
	
//	@Test
//	public void test1(){
//		System.out.println(testService.findDataTest());
		//设置Redis连接配置
//		JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(100);
//        config.setMaxIdle(50);
//        config.setMaxWaitMillis(3000);
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
        //集群
        //连接 Redis 服务
//        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("localhost", 6379);
//        jedisShardInfo1.setPassword("yuan");
//        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
//        list.add(jedisShardInfo1);
//        ShardedJedisPool pool = new ShardedJedisPool(config, list);
//        ShardedJedis jedis = pool.getResource();
//        System.out.println("服务正在运行: "+jedis.);
		//连接本地的 Redis 服务
//        Jedis jedis = new Jedis("localhost");
//        System.out.println("连接成功");
        //查看服务是否运行
//        System.out.println("服务正在运行: "+jedis.ping());
//	}
	
//	@Test
//    public void testRdisGet(){
//        String key = "yuan";
//        String val = redisClientTemplate.get(key);
//        System.out.println(val);
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(100);
//        config.setMaxIdle(50);
//        config.setMaxWaitMillis(3000);
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
//        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("localhost", 6379);
//        jedisShardInfo1.setPassword("yuan");
//        List<JedisShardInfo> jedisShardInfolist = new LinkedList<JedisShardInfo>();
//        jedisShardInfolist.add(jedisShardInfo1);
//        ShardedJedisPool pool = new ShardedJedisPool(config, jedisShardInfolist);
//        ShardedJedis shardedJedis = pool.getResource();
//		Jedis jedis = shardedJedis.getShard("iphone8Num");
//		jedis.watch("iphone8Num");
//		String value = jedis.get("iphone8Num");
//		System.out.println(value);
//    }
	
	//这里用@Test做多线程的单元测试会有问题——Junit当主线程退出，子线程也会退出，导致执行不完整
	//因为它@Test执行完后会调用相应的System.exit()方法离开,这个方法是用来结束当前正在运行中的java虚拟机,所以子线程就全部GG了
	//所以需要强制将主线程停住，等待子线程执行完再结束，这里用了休眠的方法或者CountDownLatch阻塞
	@Test
    public void testRdis_GAOBINGFA(){
		redisClientTemplate.set("iphone8Num", "100");
		CountDownLatch latch=new CountDownLatch(10);
		//多线程
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 1000; i++){
            executor.execute(new Redis_MiaoSha("user"+i, "iphone8Num"));
        }
        executor.shutdown();
//        try {
//			Thread.sleep(120000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        //让主线程阻塞，直到子线程运行结束或者阻塞超时
        try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		new Redis_MiaoSha("user"+1, "iphone8Num").run();
    }
	
//	@Test
//    public void testRdis_GAOBINGFA(){
//		redisClientTemplate.set("100", "iphone8Num");
//		JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(100);
//        config.setMaxIdle(50);
//        config.setMaxWaitMillis(3000);
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
//        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("localhost", 6379);
//        jedisShardInfo1.setPassword("yuan");
//        List<JedisShardInfo> jedisShardInfolist = new LinkedList<JedisShardInfo>();
//        jedisShardInfolist.add(jedisShardInfo1);
//        ShardedJedisPool pool = new ShardedJedisPool(config, jedisShardInfolist);
//        ShardedJedis shardedJedis = pool.getResource();
//		Jedis jedis = shardedJedis.getShard("iphone8Num");
//		jedis.watch("iphone8Num");
//		String value = jedis.get("iphone8Num");
//		System.out.println(value);
//        int num = Integer.parseInt(value);
//        //秒杀的商品剩余数量不为0
//        if(num > 0) {
//        	//开启事务
//            Transaction tx = jedis.multi();
//            //商品数量-1
//            tx.incrBy("iphone8Num", -1);
//            //提交事务，返回事务执行情况
//            List<Object> list = tx.exec();
//            if(list==null || list.size()<1){
//                System.out.println("yuan"+"商品抢购失败！");
//            }else{
//                for(Object success : list){
//                    System.out.println("yuan"+"("+success.toString()+")商品抢购成功,当前抢购成功的人数是："+(1-(num-100)));
//                }
//            }
//        }else{
//            System.out.println("yuan"+"商品已经被抢完了");
//        }
//        shardedJedis.close();
//    }

}