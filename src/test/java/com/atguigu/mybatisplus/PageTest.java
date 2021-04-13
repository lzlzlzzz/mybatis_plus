package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.MybatisDemo;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectPage(){

        Page<MybatisDemo> userPage = new Page<>(1, 3);

        userMapper.selectPage(userPage,null);

        userPage.getRecords().forEach(System.out::println); // 当前页的所有数据

        System.out.println(userPage.getCurrent()); // 当前页数
        System.out.println(userPage.getSize()); // 当前页数据量
        System.out.println(userPage.getTotal()); // 总数据量
        System.out.println(userPage.getPages()); // 总页数
        System.out.println(userPage.hasPrevious()); // 是否有上一页
        System.out.println(userPage.hasNext()); // 是否有下一页
    }
}
