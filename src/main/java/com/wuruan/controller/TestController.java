package com.wuruan.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.hibernate.validator.internal.metadata.provider.ProgrammaticMetaDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.form.InputTag;

import com.wuruan.dao.ToDoItemDao;
import com.wuruan.model.ToDoItem;
import com.wuruan.util.EmailUtil;

@RestController
@RequestMapping("item")
@CrossOrigin
public class TestController {
	
	@Autowired
	ToDoItemDao toDoItem;  

	private List<ToDoItem> itemList = new ArrayList<ToDoItem>();

	@GetMapping("initDate")
	public Object hello() {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

		String now = format1.format(new Date().getTime());
		if (itemList.size() == 0) {
			itemList.add(new ToDoItem(UUID.randomUUID().toString().replaceAll("-", ""), "洗漱", "刷牙洗脸吃饭", now));

			itemList.add(new ToDoItem(UUID.randomUUID().toString().replaceAll("-", ""), "出门", "出门带上电脑和课本", now));

			itemList.add(new ToDoItem(UUID.randomUUID().toString().replaceAll("-", ""), "上课", "做笔记听讲", now));
		}
		return itemList;
	}

	@PostMapping("add")
	public Map<String, Object> addItem(@RequestBody Map<String, Object> paramData) {

		int oldLength = itemList.size();

		String inputItem = (String) paramData.get("inputItem");

		String[] arr = inputItem.split(",");

		String title = arr[0];

		String content = arr[1];
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		String now = format1.format(new Date().getTime());
		itemList.add(new ToDoItem(UUID.randomUUID().toString().replaceAll("-", ""), title, content, now));

		int newLength = itemList.size();

		int num = (newLength - oldLength) == 1 ? 0 : 1;

		// showItemList();
		String status = num == 0 ? "插入数据成功" : "插入数据失败";

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("插入数据的条数", newLength - oldLength);
		result.put("status", status);
		result.put("num", num);

		return result;
	}

	@PostMapping("search")
	public Object searchItem() {
		return itemList;

	}

	@PostMapping("update")
	public Map<String, Object> updateItem(@RequestBody Map<String, Object> paramData) {

		String content = (String) paramData.get("content");

		String id = (String) paramData.get("id");

		Map<String, Object> result = new HashMap<String, Object>();
		String status = "修改失败";
		try {
			for (int i = 0; i < itemList.size(); i++) {
				if (id.equals(itemList.get(i).getId())) {
					itemList.get(i).setContent(content);
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
					String now = format1.format(new Date().getTime());
					itemList.get(i).setDate(now);
				}
			}
			status = "0";

		} catch (Exception e) {
			// TODO: handle exception
			status = "1";
		}

		result.put("status", status);// data.status

		return result;

	}

	@GetMapping("deleteItem/{id}")
	public String deleteItem(@PathVariable String id) {

		int oldLength = itemList.size();
		for (int i = 0; i < itemList.size(); i++) {
			if (id.equals(itemList.get(i).getId())) {
				itemList.remove(i);
			}
		}
		int newLength = itemList.size();

		int cha = oldLength - newLength;

		String status = "1";

		status = cha == 1 ? "0" : "1";

		return status;
	}

	@GetMapping("getTitle")
	public String getTitle() {
		
		return toDoItem.getTitle("title");
	}
	
	@GetMapping("sendEmail/{qq}")
	public String getTitle(@PathVariable("qq") String qq ) {
		
		EmailUtil.sendTest(qq);
		
		return "发送成功！";
	}
	

	public String transformItemLitTostr() {

		String res = "";
		for (int i = 0; i < itemList.size(); i++) {
			res += "\n标题：" + itemList.get(i).getTitle() + "内容：" + itemList.get(i).getContent() + "截至时间："
					+ itemList.get(i).getDate();

		}

		return res;
	}

}