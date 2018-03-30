package module;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ootv.module.JedisService;
import cn.ootv.module.UserService;

import com.guang.po.User;

public class TestUser extends BaseTest{
	
	@Autowired 
	private UserService userService;
	@Autowired
	private JedisService jedisService;
	
	@Test
    public void testAddUser(){
       User chencj = new User();
       chencj.setName("陈婵娟");
       chencj.setPassword("root");
       chencj.setTelphone("13003659599");
       chencj.setUpdateTime(new Date());
       userService.addUser(chencj);
	}
	
	@Test
	public void getCacheUser(){
		jedisService.putInCache("people", "ccj.name", "chenchanjuan");
		Object name = jedisService.getFromCache("people", "ccj.name");
		System.out.println(name);
	}
}
