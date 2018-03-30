package com.guang.mapper;

import com.guang.po.User;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

/**
 * 
 * @author haoxz11MyBatis 
 * @created Thu Mar 30 14:09:31 CST 2017
 * @version $Id: DefaultCommentGenerator.java,v 1.1 2013/10/28 07:59:58 haoxz11 Exp $
 * @haoxz11MyBatis
 */
public interface UserMapper {

	/**
	 * @haoxz11MyBatis
	 */
	int insertUser(User record);

	/**
	 * @haoxz11MyBatis
	 */
	User getByPrimaryKey(Long id);

	/**
	 * @haoxz11MyBatis
	 */
	int updateUser(User record);

	/**
	 * 搜索列表，带分页
	 * @haoxz11MyBatis
	 */
	List<User> getListByWhere(HashMap<String, Object> searchMap,
			RowBounds rowBounds);

	/**
	 * 搜索列表
	 * @haoxz11MyBatis
	 */
	List<User> getListByWhere(HashMap<String, Object> searchMap);

	/**
	 * 得到搜索的记录数量
	 * @haoxz11MyBatis
	 */
	int getCountByWhere(HashMap<String, Object> searchMap);
}