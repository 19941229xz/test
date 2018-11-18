package com.wuruan.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//dao database access object  数据库连接对象
@Mapper
public interface UserDao{
	
	
	@Select("select pwd from users where name=#{name1}")
	public String getPwdByName(String name1);
	
}