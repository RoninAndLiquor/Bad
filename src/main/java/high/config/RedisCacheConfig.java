package high.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


public class RedisCacheConfig extends CachingConfigurerSupport {

	public JedisConnectionFactory redisConnectionFactory(){
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName("127.0.0.1");
		redisConnectionFactory.setPort(6379);
		return redisConnectionFactory;
	}
	
	public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory cf){
		RedisTemplate<String,String> redisTemplate = new RedisTemplate<String,String>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}
	
	public CacheManager cachaManager(RedisTemplate redisTemplate){
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(3000);
		return cacheManager;
	}
	
}
