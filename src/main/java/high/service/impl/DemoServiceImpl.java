package high.service.impl;

import high.entity.Address;
import high.entity.User;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl {

	@Cacheable(value="usercache" ,keyGenerator="wiselyKeyGenerator")
	public User findUser(Long id,String firstName,String lastName){
		System.out.println("无缓存时候调用这里");
		return new User(id,firstName,lastName);
	}
	
	
	@Cacheable(value="addresscache",keyGenerator="wiselyKeyGenerator")
	public Address findAddress(Long id,String province,String city){
		System.out.println("无缓存时候调用这里");
		return new Address(id,province,city);
	}
	
}
