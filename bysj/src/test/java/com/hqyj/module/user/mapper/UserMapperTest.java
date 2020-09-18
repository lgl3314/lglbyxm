package com.hqyj.module.user.mapper;


import com.hqyj.module.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author lykgogo
 * @Date 2020/9/10 15:14
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindUserByUserId(){

        User user = userMapper.findUserByUserId(2L);
        System.out.println("user = " + user);
    }
}
