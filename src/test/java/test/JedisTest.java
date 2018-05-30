package test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

public class JedisTest {
	
	//集群  初始化设置
	static ShardedJedisPool pool;
	static {
		JedisPoolConfig config =new JedisPoolConfig();  //Jedis池配置
        config.setMaxTotal(500);  //最大活动的对象个数
        config.setMaxIdle(1000 * 60);  //对象最大空闲时间
        config.setMaxWaitMillis(1000 * 10);  //获取对象时最大等待时间
        config.setTestOnBorrow(false);
        config.setTestOnReturn(true);
        String host1 = "localhost";
        int port1 = 6379;
//        String host2 = "10.10.224.48";
//        int port2 = 6379;
        List<JedisShardInfo> jdsInfoList =new ArrayList<JedisShardInfo>(2);
        JedisShardInfo info1 = new JedisShardInfo(host1, port1);
        info1.setPassword("yuan");
//        JedisShardInfo info2 = new JedisShardInfo(host2, port2);
//        info2.setPassword("redis.360buy");
        jdsInfoList.add(info1);
//        jdsInfoList.add(info2);
        //客户端jedis的一致性哈稀进行分片原理：初始化ShardedJedisPool的时候，会将上面程序中的jdsInfoList数据进行一个算法技术，主要计算依据为jdsInfoList中的index位置来计算
        pool =new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH,Sharded.DEFAULT_KEY_TAG_PATTERN);
	}
	
	public static void main(String[] args) {
		ShardedJedis jds = pool.getResource();
		String key = "yuan";
		String value = "我是李伟源";
		//jds会根据key的值依据一致性哈希分片算法进行数据分配保存
		//如果有多个redis服务器配置在pool中，改变key值，可以看到它们会被分配到不同的服务器上
		System.out.println(key+":"+jds.getShard(key).getClient().getHost());
		System.out.println(jds.set(key,value));
//		try {
//			System.out.println(jds.set(key,new String(value.getBytes("UTF-8"), "GBK")));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(jds.get(key));
//		try {
//		    System.out.println(new String(jds.get(key).getBytes("UTF-8"), "UTF-8"));
//	    } catch (UnsupportedEncodingException e) {
//		    // TODO Auto-generated catch block
//		    e.printStackTrace();
//	    }
//		try {
//			System.out.println(new String(getUTF8BytesFromGBKString(jds.get(key)), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	//获取gbk编码字符串字节流
	public static byte[] getUTF8BytesFromGBKString(String gbkStr) {  
	    int n = gbkStr.length();  
	    byte[] utfBytes = new byte[3 * n];  
	    int k = 0;  
	    for (int i = 0; i < n; i++) {  
	        int m = gbkStr.charAt(i);  
	        if (m < 128 && m >= 0) {  
	            utfBytes[k++] = (byte) m;  
	            continue;  
	        }  
	        utfBytes[k++] = (byte) (0xe0 | (m >> 12));  
	        utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));  
	        utfBytes[k++] = (byte) (0x80 | (m & 0x3f));  
	    }  
	    if (k < utfBytes.length) {  
	        byte[] tmp = new byte[k];  
	        System.arraycopy(utfBytes, 0, tmp, 0, k);  
	        return tmp;  
	    }  
	    return utfBytes;  
	}  
}