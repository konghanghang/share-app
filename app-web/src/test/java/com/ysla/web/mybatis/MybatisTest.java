package com.ysla.web.mybatis;

import com.ysla.web.mybatis.mapper.IUserMapper;
import com.ysla.web.mybatis.model.User;
import com.ysla.web.mybatis.sqlSession.IMySqlSession;
import com.ysla.web.mybatis.sqlSession.MySqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisTest {

    @Test
    public void test(){
        IMySqlSession mySqlSession = new MySqlSession();

        IUserMapper userMapper = mySqlSession.getMapper(IUserMapper.class);

        User user = userMapper.findUserById(9);

        System.out.println(user);
    }

}
