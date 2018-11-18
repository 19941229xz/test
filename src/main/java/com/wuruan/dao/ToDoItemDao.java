package com.wuruan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ToDoItemDao {

	/**
	 * 从数据库获取所有的代办事项 List<Map>
	 * @return
	 */
	@Select("select * from toDoItems")
	public List<Map<String,Object>> getAllToDoItems();
	
	/**
	 * 给数据表 toDoItems添加一行数据
	 * @param item
	 * @return
	 */
	@Insert("insert into toDoItems(id,title,content,date)values(#{id},#{title},#{content},#{date})")
	public int addItem(Map<String, Object> item);
	
	
	/**
	 * 修改数据  通过id
	 */
	@Update("update toDoItems set title=#{title},content=#{content},"
			+ "date=#{date} where id=#{id}")
	public int updateItem(Map<String,Object> item);

	@Select("select web_config.value from web_config where web_config.key=#{key} ")
	public String getTitle(@Param("key")  String yyy);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
