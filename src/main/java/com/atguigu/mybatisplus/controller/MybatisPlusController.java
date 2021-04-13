package com.atguigu.mybatisplus.controller;

import com.atguigu.mybatisplus.entity.MybatisDemo;
import com.atguigu.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MybatisPlusController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteById(long id){
        int i = userMapper.deleteById(id);
        if (i == 1){
            return "success";
        }
        return "fail";
    }

    @RequestMapping("/search")
    @ResponseBody
    public MybatisDemo searchById(int id){
        MybatisDemo user = userMapper.selectById(id);
        return user;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id){
        MybatisDemo mybatisDemo = new MybatisDemo();
        mybatisDemo.setId(id);
        mybatisDemo.setAge(20);
        mybatisDemo.setEmail("aaa");
        int i = userMapper.updateById(mybatisDemo);
        if (i == 1){
            return "success";
        }
        return "fail";
    }
}
