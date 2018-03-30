package cn.ootv.module;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisService {
	private static final Logger logger = LoggerFactory
			.getLogger(JedisService.class);

	@Resource
	private JedisPool jedisPool;
	private static HessianCodecFactory hessianCodecFactory = new HessianCodecFactory();
	public static final int REF_SECONDS = 180;

	public void returnResource(Jedis jedis) {
		if (jedis != null)
			try {
				this.jedisPool.returnResourceObject(jedis);
			} catch (Exception e) {
				this.jedisPool.returnResourceObject(jedis);
			}
	}

	private String getCacheNameString(String type, Object key) {
		StringBuilder cacheName = new StringBuilder();
		cacheName.append(type);
		if ((key != null) && (key.toString().length() > 0)) {
			cacheName.append(":").append(key);
		}
		return cacheName.toString();
	}

	private byte[] getCacheName(String type, Object key) {
		return getCacheNameString(type, key).getBytes();
	}

	private byte[] getSerializable(Object value) {
		return hessianCodecFactory.serialize(value);
	}

	private Object getDeserialization(byte[] value) {
		if (value == null)
			return null;
		try {
			return hessianCodecFactory.deserialize(value);
		} catch (Exception e) {
		}
		return null;
	}

	public void putInCache(String type, Object key, Object value) {
		putInCache(type, key, value, 180);
	}

	public void putNoTimeInCache(String type, Object key, Object value) {
		if (value != null)
			putInCache(type, key, value, -1);
	}

	public void putInCache(String type, Object key, Object value, int seconds) {
		if (value != null) {
			byte[] cacheName = getCacheName(type, key);
			byte[] v = getSerializable(value);
			if (v != null) {
				Jedis jedis = null;
				try {
					jedis = this.jedisPool.getResource();
					if (seconds < 1)
						jedis.set(cacheName, v);
					else
						jedis.setex(cacheName, seconds, v);
				} catch (Exception e) {
					logger.error("cache " + getCacheName(type, key)
							+ " socket error。");
				} finally {
				}
			}
		}
	}

	public void deleteCache(String type, Object key) {
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			jedis.del(getCacheName(type, key));
		} catch (Exception e) {
			logger.error("cache " + getCacheName(type, key) + " socket error。");
		} finally {
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getFromCache(Class<T> clazz, String type, Object key) {
		return (T) getFromCache(type, key);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> getListFromCache(Class<T> clazz, String type, Object key) {
		return (List) getFromCache(type, key);
	}

	public Object getFromCache(String type, Object key) {
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			byte[] v = jedis.get(getCacheName(type, key));
			return getDeserialization(v);
		} catch (Exception e) {
			logger.debug("cache " + getCacheName(type, key) + " error。");
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	public long putCounterCache(String type, Object key, long value, int ttl) {
		long ret = 0L;
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			jedis.setex(getCacheNameString(type, key), ttl,
					String.valueOf(value));
		} finally {
			returnResource(jedis);
		}
		return ret;
	}

	public void expire(String type, Object key, int ttl) {
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			jedis.expire(getCacheNameString(type, key), ttl);
		} finally {
			returnResource(jedis);
		}
	}

	public long incrCounterCache(String type, Object key, long value) {
		long ret = 0L;
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			if (value >= 0L)
				ret = jedis.incrBy(getCacheName(type, key), Math.abs(value))
						.longValue();
			else
				ret = jedis.decrBy(getCacheName(type, key), Math.abs(value))
						.longValue();
		} finally {
			returnResource(jedis);
		}
		return ret;
	}

	public long getCounter(String type, Object key) {
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			byte[] v = jedis.get(getCacheName(type, key));
			if (v != null) {
				return NumberUtil.parseLong(new String(v));
			}
			return 0L;
		} catch (Exception e) {
			logger.debug("cache " + getCacheName(type, key) + " error。");
		} finally {
			returnResource(jedis);
		}
		return 0L;
	}

	public boolean exists(String type, Object key) {
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			boolean ret = jedis.exists(getCacheName(type, key)).booleanValue();
			return ret;
		} catch (Exception e) {
			logger.debug("cache " + getCacheName(type, key) + " error。");
		} finally {
			returnResource(jedis);
		}
		return false;
	}

	public void close() {
		try {
			logger.info("cache shutdown！");
		} catch (Exception e) {
			logger.error("cache clear socket error。");
		}
	}

	public static void main(String[] args) {
		// JedisService service =
		// (JedisService)ServiceManager.getService(JedisService.class);
		//
		// if (args.length >= 3) {
		// System.out.println(service.getFromCache(args[0], args[1]));
		// } else if (args.length >= 2) {
		// service.deleteCache(args[0], args[1]);
		// logger.info(args[0] + " " + args[1] + " Cache删除完成.");
		// }
		// service.close();
	}
}