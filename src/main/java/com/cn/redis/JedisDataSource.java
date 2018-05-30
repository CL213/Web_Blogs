package com.cn.redis;

import redis.clients.jedis.ShardedJedis;

//管理Jedis实例的生成和回收
public interface JedisDataSource {
	
	public ShardedJedis getRedisClient();
	
	public void returnResource(ShardedJedis shardedJedis);
	
	public void returnResource(ShardedJedis shardedJedis, boolean broken);
	
}