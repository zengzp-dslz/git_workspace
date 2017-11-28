package cn.oot.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guang.po.User;

import cn.ootv.module.UserService;


/**
 * @author zengzp
 * @title UserController
 * @description 用户控制层
 * @date 2017年3月29日 下午2:11:53
 * @version V1.0
 */
@RequestMapping("/user")
@Controller
public class UserController{
    
//	@Autowired
//	private UserService userService; //service暴露的协议接口
	
//	@RequestMapping("/getUserDeatil.html")
//	public String getUserDeatil(Model model,Long userId) {
//		User user = userService.getUserById(userId);
//		model.addAttribute("user", user);
//		return "userDetail";
//	}
}
