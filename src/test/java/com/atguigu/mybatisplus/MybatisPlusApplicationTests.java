package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.MybatisDemo;
import com.atguigu.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelectMaps(){

		Map<String, Object> map = new HashMap<>();

		map.put("name","bbb");
		map.put("age",20);

		List<MybatisDemo> users = userMapper.selectByMap(map);

		users.forEach(System.out::println);
	}

	@Test
	public void testSelectBatchIds(){

		List<MybatisDemo> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));

		users.forEach(System.out::println);
	}

	@Test
	public void testUpdate(){

		MybatisDemo user = new MybatisDemo();

		user.setId(1156820418229760001l);
		user.setName("bbb");

		userMapper.updateById(user);
	}

	@Test
	public void testInsert(){

		MybatisDemo user = new MybatisDemo();

		user.setName("aaa");
		user.setAge(20);
		user.setEmail("123@qq.com");

		userMapper.insert(user);
	}

	@Test
	public void testUserList() {

		List<MybatisDemo> users = userMapper.selectList(null);

		users.forEach(System.out::println);
	}

}
