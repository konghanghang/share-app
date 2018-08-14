package com.ysla.provider;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PasswordTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd(){
        String result = stringEncryptor.encrypt("2222");
        System.out.println(result);
    }

}
