package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeletedTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testDeletedById(){

        userMapper.deleteById(1156787245731102722l);
    }

    @Test
    public void testDeletedBatchIds(){

        userMapper.deleteBatchIds(Arrays.asList(6l,7l));
    }
}
