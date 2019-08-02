package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryWrapperTests {

    @Autowired
    private UserMapper userMapper;

    // ge:>= gt:> le:<= lt:< isNull isNotNull
    @Test
    public void testDelete(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.isNull("name")
                .ge("age",12)
                .isNotNull("email");

        int delete = userMapper.delete(userQueryWrapper);

        System.out.println("delete return count =" + delete);
    }

    // eq:=  ne:!=
    @Test
    public void testSelectOne(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq("name","Tom");

        User user = userMapper.selectOne(userQueryWrapper);

        System.out.println(user);
    }

    // between notBetween
    @Test
    public void testSelectCount(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.notBetween("age",10,20);

        Integer count = userMapper.selectCount(userQueryWrapper);

        System.out.println(count);
    }

    // allEq:多条件查询
    @Test
    public void testSelectList(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        Map<String, Object> map = new HashMap<>();

        map.put("id",2l);
        map.put("name","jack");
        map.put("age",20);

        userQueryWrapper.allEq(map);

        userMapper.selectList(userQueryWrapper).forEach(System.out::println);
    }

    // like:包含... notLike likeLeft:以...结尾 likeRight:以...开头
    @Test
    public void testSelectMaps(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.like("name","e");
        userQueryWrapper.likeRight("email","t");

        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);

        maps.forEach(System.out::println);
    }

    // in:相当于查询条件in notIn exists:检查数据是否存在，返回boolean值，以此来判断外层sql执不执行 notExists
    // inSql:inSql("age","1,2,3,4") -> age in(1,2,3,4,) inSql("id","select id from table where id < 3") -> id in(select id from table where id < 3)   notinSql
    @Test
    public void testSelectObjs(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

//        userQueryWrapper.exists("select * from user where age = 20");
//        userQueryWrapper.in("id",1,2,3,4);
//        userQueryWrapper.inSql("id","1,2,3,4");
        userQueryWrapper.inSql("id","select id from user where id < 4");

        List<Object> objects = userMapper.selectObjs(userQueryWrapper);

        objects.forEach(System.out::println);
    }

    // or and
    @Test
    public void testUpdate1(){

        User user = new User();

        user.setAge(99);
        user.setName("Andy");

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        updateWrapper.like("name","h")
                .or()
                .between("age",25,30);

        userMapper.update(user,updateWrapper);
    }

    // 嵌套or 嵌套and
    @Test
    public void testUpdate2(){

        User user = new User();

        user.setAge(99);
        user.setName("Andy");

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        updateWrapper.like("name","h")
                .or(i -> i.eq("name","李白").ne("age",20));

        int result = userMapper.update(user, updateWrapper);

        System.out.println("result = " + result);
    }

    // orderBy orderByDesc orderByAsc
    @Test
    public void testSelectListOrderBy(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // condition：是否启用排序 isAsc：是否是升序
//        queryWrapper.orderBy(false,true,"age");
//        queryWrapper.orderByAsc("age"); // 升序排列
        queryWrapper.orderByDesc("age"); // 降序排列

        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);
    }

    // last
    @Test
    public void testSelectListLast(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.last("limit 1");

        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);
    }

    // 指定要查询的列
    @Test
    public void testSelectListColumn(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id","name","age");

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);

        maps.forEach(System.out::println);
    }

    // set setSql
    @Test
    public void testUpdateSet(){

        User user = new User();

        user.setAge(99);

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        updateWrapper.like("name","h")
                .set("name","老李头")
                .setSql("email = '123@qq.com'");

        int update = userMapper.update(user, updateWrapper);

        System.out.println("result = " + update);
    }
}








