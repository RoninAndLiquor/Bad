package high.config;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javax.xml.ws.Action;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	
	@Bean
	public KeyGenerator wiselyKeyGenerator(){
		KeyGenerator keyGenerator = new KeyGenerator(){

			@Action
			public Object generate(Object target, Method method,
					Object... params) {
				StringBuilder sb = new StringBuilder();
				String[] value = new String[1];
				//sb.append(target.getClass().getName());
				//sb.append(method.getName());
				Method[] methods = target.getClass().getMethods();
				Annotation[] anno = new Annotation[5];
				int flag=0;
				for(Method m:methods){
					if(m.getName().equals(method.getName())){
						Annotation[] annotations = m.getAnnotations();
						for(Annotation a:annotations){
							anno[flag] = a;
							flag++;
						}
					}
				}
				for(Annotation a:anno){
					if(a!=null){
						if(a instanceof Cacheable){
							Cacheable cacheable = (Cacheable) a;
							value = cacheable.value();
						}
						if(a instanceof CachePut){
							CachePut cachePut = (CachePut) a;
							value = cachePut.cacheNames();
						}
						if(a instanceof CacheEvict){
							CacheEvict cacheEvict = (CacheEvict) a;
							value = cacheEvict.value();
						}
					}
				}
				if(value[0] !=null){
					sb.append(value[0]);
				}
				for(Object obj:params){
					String checkParam = checkParam(obj);
					sb.append(":"+checkParam);
				}
				return sb.toString();
			}
			
		};
		return keyGenerator;
	}
	
	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate){
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		rcm.setDefaultExpiration(1800);
		return rcm;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om  = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY);  
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
        jackson2JsonRedisSerializer.setObjectMapper(om);  
        template.setValueSerializer(jackson2JsonRedisSerializer);  
        template.afterPropertiesSet();  
        return template; 
	}
	
	private String checkParam(Object obj){
		ObjectMapper om = new ObjectMapper();
		if(obj instanceof Integer){
			return obj.toString();
		}
		if(obj instanceof String){
			return obj.toString();
		}
		if(obj instanceof Long){
			return obj.toString();
		}
		if(obj instanceof Double){
			return obj.toString();
		}
		if(obj instanceof Float){
			return obj.toString();
		}
		if(obj instanceof List<?>){
			return obj.toString();
		}
		LinkedMap readValue = new LinkedMap();
		try {
			readValue = om.readValue(om.writeValueAsString(obj), LinkedMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object = readValue.get(readValue.get(0));
		return object.toString();
	}
	
}
