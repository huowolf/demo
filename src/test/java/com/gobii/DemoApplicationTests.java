package com.gobii;

import com.gobii.mapper.UserinfoMapper;
import com.gobii.model.Userinfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Test
    public void initDB() {

        ArrayList<Userinfo> userinfos = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            Userinfo userinfo = new Userinfo();
            userinfo.setName("test"+i);
            if(i<=9){
                userinfo.setPhone("1358280430"+i);
            }else{
                userinfo.setPhone("135828043"+i);
            }
            userinfo.setEmail("xxxxxxxxx"+i+"@qq.com");
            userinfos.add(userinfo);
        }

        userinfoMapper.insertList(userinfos);
    }

}
