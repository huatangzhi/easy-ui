package com.superbool.easyui.dao;

import com.google.common.base.Splitter;
import com.superbool.easyui.EasyUIApplication;
import com.superbool.easyui.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by superbool on 2016/11/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyUIApplication.class)
public class UserInfoDaoTest {

    @Autowired
    UserInfoDao userInfoDao;

    @Test
    public void getTest() {
        try {
            List<UserInfo> userInfos = userInfoDao.userList(0, 3);
            System.out.println(userInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertTest() {
        for (int i = 0; i < 100; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setCardId(String.valueOf(i));
            userInfo.setName("name" + i);
            userInfo.setDepartment(String.valueOf(i % 10));
            try {
                int count = userInfoDao.userAdd(userInfo);
                System.out.println(count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void checkInsertTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setCardId("2");
        userInfo.setName("abc");
        userInfo.setDepartment("3");
        try {
            userInfoDao.userAddALL(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countTest() {
        try {
            int total = userInfoDao.userCount();
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void intoData() {
        String line;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("source.txt");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\s+");
                try {
                    UserInfo userInfo = new UserInfo(data[2].trim(), data[1].trim(), data[0].trim());
                    userInfoDao.userAdd(userInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
