package com.cn.redis;

import java.util.LinkedList;
import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

public class Redis_MiaoSha implements Runnable {
	
	private String productNumKey;
	private String user;
	
	public Redis_MiaoSha(String user, String productNumKey){
        this.productNumKey=productNumKey;
		this.user = user;
    }
	
	@Override
	public void run() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
	        config.setMaxTotal(300);
	        config.setMaxIdle(100);
	        config.setMaxWaitMillis(100000);
	        config.setTestOnBorrow(false);
//	        config.setTestOnReturn(true);
	        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("localhost", 6379);
	        jedisShardInfo1.setPassword("yuan");
	        List<JedisShardInfo> jedisShardInfolist = new LinkedList<JedisShardInfo>();
	        jedisShardInfolist.add(jedisShardInfo1);
	        ShardedJedisPool pool = new ShardedJedisPool(config, jedisShardInfolist);
	        ShardedJedis shardedJedis = pool.getResource();
			Jedis jedis = shardedJedis.getShard(productNumKey);
			jedis.watch(productNumKey);
			String value = jedis.get(productNumKey);
//			System.out.println(productNumKey);
//			System.out.println(value);
	        int num = Integer.parseInt(value);
	        System.out.println(num);
	        //秒杀的商品剩余数量不为0
	        if(num > 0) {
	        	//开启事务
	            Transaction tx = jedis.multi();
	            //商品数量-1
	            tx.incrBy(productNumKey, -1);
	            //提交事务，返回事务执行情况
	            List<Object> list = tx.exec();
	            if(list==null || list.size()==0){
	                System.out.println(user+"商品抢购失败！");
	            }else{
	                for(Object success : list){
	                    System.out.println(user+"("+success.toString()+")商品抢购成功,当前抢购成功的人数是："+(1-(num-100)));
	                }
	            }
	        }else{
	            System.out.println(user+"商品已经被抢完了");
	        }
	        shardedJedis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		}
	}
	
}