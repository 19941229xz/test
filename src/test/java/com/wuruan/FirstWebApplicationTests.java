package com.wuruan;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wuruan.dao.ToDoItemDao;
import com.wuruan.dao.UserDao;
import com.wuruan.util.EmailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstWebApplicationTests {

	@Autowired
	UserDao ud;
	
	@Autowired
	ToDoItemDao todoitemdao;
	
	@Autowired
	EmailUtil eu;
	
//	
	@Test
	public void contextLoads() {
//		System.out.println("tom 的密码是："+ud.getPwdByName("tom"));
		
//		List<Map<String,Object>> listMaps=tdid.getAllToDoItems();
//		System.out.println("代办事项如下：");
//		for (int i = 0; i < listMaps.size(); i++) {
//			System.out.println("标题："+listMaps.get(i).get("title")+" 内容："+listMaps.get(i).get("content"));
//		}
	}
	
	public void showAllItems(){
		List<Map<String,Object>> listMaps=todoitemdao.getAllToDoItems();
		System.out.println("代办事项如下：");
		for (int i = 0; i < listMaps.size(); i++) {
			System.out.println("标题："+listMaps.get(i).get("title")+" "
					+ "内容："+listMaps.get(i).get("content")+
					" 时间："+listMaps.get(i).get("date"));
		}
	}
	
	@Test
	public void testAddItem(){
		
		showAllItems();
		
		//add item
		Map<String,Object> item=new HashMap<String,Object>();
		
		item.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
		item.put("title", "这是一个测试标题13434143");
		item.put("content", "这是测试内容51421414");
		item.put("date", new Date().toString());

		int num=todoitemdao.addItem(item);
		if(num==1){
			System.out.println("添加成功");
		}else{
			System.out.println("添加失败");
		}
		
		
		
		showAllItems();
		
		
		
	}
	
	@Test
	public void testUpdateItem(){
		
		showAllItems();
		
		//add item
		Map<String,Object> item=new HashMap<String,Object>();
		
		item.put("id", "tquyetiu");//这是打篮球事项的 id
		item.put("title", "洗澡11");
		item.put("content", "今天下午洗澡");
		item.put("date", new Date().toString());

		int num=todoitemdao.updateItem(item);
		if(num==1){
			System.out.println("修改成功");
		}else{
			System.out.println("修改失败");
		}
		
		showAllItems();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
