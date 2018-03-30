package com.guang.po;

import aicai.vo.BaseVo;
import java.util.Date;

/**
 * 
 * @author haoxz11MyBatis 
 * @created Thu Mar 30 14:09:31 CST 2017
 * @version $Id: DefaultCommentGenerator.java,v 1.1 2013/10/28 07:59:58 haoxz11 Exp $
 * @haoxz11MyBatis
 */
public class User extends BaseVo {

	/**
	 * @haoxz11MyBatis
	 */
	transient private static final long serialVersionUID = -1L;
	/**
	 * 字段：用户ID
	 * @haoxz11MyBatis
	 */
	private Long id;
	/**
	 * 字段：手机号
	 * @haoxz11MyBatis
	 */
	private String telphone;
	/**
	 * 字段：密码
	 * @haoxz11MyBatis
	 */
	private String password;
	/**
	 * 字段:user.name
	 * @haoxz11MyBatis
	 */
	private String name;
	/**
	 * 字段:user.create_time
	 * @haoxz11MyBatis
	 */
	private Date createTime;
	/**
	 * 字段:user.update_time
	 * @haoxz11MyBatis
	 */
	private Date updateTime;

	/**
	 * 读取：用户ID
	 * @return  user.id
	 * @haoxz11MyBatis
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：用户ID
	 * @param id  user.id
	 * @haoxz11MyBatis
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 读取：手机号
	 * @return  user.telphone
	 * @haoxz11MyBatis
	 */
	public String getTelphone() {
		return telphone;
	}

	/**
	 * 设置：手机号
	 * @param telphone  user.telphone
	 * @haoxz11MyBatis
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	/**
	 * 读取：密码
	 * @return  user.password
	 * @haoxz11MyBatis
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置：密码
	 * @param password  user.password
	 * @haoxz11MyBatis
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 读取：user.name
	 * @return  user.name
	 * @haoxz11MyBatis
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：user.name
	 * @param name  user.name
	 * @haoxz11MyBatis
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 读取：user.create_time
	 * @return  user.create_time
	 * @haoxz11MyBatis
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：user.create_time
	 * @param createTime  user.create_time
	 * @haoxz11MyBatis
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 读取：user.update_time
	 * @return  user.update_time
	 * @haoxz11MyBatis
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置：user.update_time
	 * @param updateTime  user.update_time
	 * @haoxz11MyBatis
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}