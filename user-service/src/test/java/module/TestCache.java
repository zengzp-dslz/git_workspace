package module;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

public class TestCache{
	
	 //未分片客户端连接  
    private Jedis jedis;  
    //未分片客户端连接池  
    private JedisPool jedisPool;  
    //jedispool配置  
    private JedisPoolConfig config;  
      
    public void initJedisPoolConfig() {  
        config = new JedisPoolConfig();  
        config.setMaxTotal(100);  
        config.setMaxIdle(10);  
        config.setMaxWaitMillis(1000L);  
        config.setTestOnBorrow(true);  
        config.setTestOnReturn(true);  
    } 
    
    @Test
    public void testStr(){
    	int index = "/template/page".lastIndexOf("/");
    	System.out.println("/template/page".substring(index));
    }
      
    public void initJedisPool() {  
        initJedisPoolConfig();  
        jedisPool = new JedisPool(config,"43.248.79.236",7379);  
    }  
      
      
    public Jedis getJedis() {  
        jedis = this.jedisPool.getResource();  
        return jedis;  
    }  
      
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public void returnResource(Pool pool, Object redis) {  
        if (redis != null) {  
            pool.returnResourceObject(redis);  
        }  
    }  
      
      
    public String get(String key) {    
        String value = null;    
        try {    
            jedis = getJedis();  
            value = jedis.get(key);    
        } catch (Exception e) {    
            e.printStackTrace();    
            // 释放资源    
            returnResource(jedisPool, jedis);
        } finally {    
            returnResource(jedisPool, jedis);    
        }    
        return value;    
    }   
      
      
    @Test  
    public void test() {  
        initJedisPool();  
        jedis = getJedis();  
        jedis.set("momo", "nono");  
        //shardedJedis.set("chiwei", "lining");  
        System.out.println(jedis.get("momo"));  
        //System.out.println(shardedJedis.get("chiwei"));  
    }  
	
	@Test
	public void testGetCache(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(10);
		config.setMaxTotal(300);
		config.setMaxWaitMillis(2000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		JedisPool pool = new JedisPool(config,"43.248.79.236",6379,2000,"root");
		Jedis jedis = pool.getResource();
		pool.returnResourceObject(jedis);
		pool.close();
	}

}
