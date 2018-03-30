package cn.ootv.module;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.mapper.UserMapper;
import com.guang.po.User;

/**
 * @author zengzp 
 * @date 2017年5月17日 下午5:13:08
 * @describtion 用户服务类
 * @version 1.0
 */
@Service
public class UserService {
     @Resource
     private UserMapper userMapper;
     @Resource
     private JedisService jedisService; //缓存客户端工具类
     
     public int addUser(User user){
    	 return userMapper.insertUser(user);
     }
     
	 /**
	 * @describtion 
	 * @author zengzp
	 * @date 2017年5月17日 下午5:15:57
	 * @version 1.0
	 */
	public User getUserById(Long userId) {
		User user = jedisService.getFromCache(User.class,"USERINFO", userId);
		System.out.println("根据用户ID从缓存中获取用户信息");
		if (user == null) {
			System.out.println("无此用户缓存信息");
			User dbuser =userMapper.getByPrimaryKey(userId);
			System.out.println("从数据库获取对象 user:"+dbuser);
			jedisService.putInCache("USERINFO", userId, dbuser);
			System.out.println("缓存对象6分钟");
			return dbuser;
		}
		System.err.println("返回缓存数据");
		return user;
	}
}
