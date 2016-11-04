package com.superbool.easyui.dao;

import com.superbool.easyui.EasyUIApplication;
import com.superbool.easyui.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            List<UserInfo> userInfos = userInfoDao.getByPage(0, 3);
            System.out.println(userInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getByCardIdTest() {
        try {
            List<UserInfo> userInfos = userInfoDao.getByCardId("123");
            System.out.println(userInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getById() {
        try {
            UserInfo userInfo = userInfoDao.getById(1);
            System.out.println(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateSameIdTest() {
        try {
            int result = userInfoDao.updateSameId("123");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertOneTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setCardId("1234");
        userInfo.setName("张三");
        userInfo.setDepartment("12321");
        try {
            int count = userInfoDao.userAdd(userInfo);
            System.out.println(count);
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
    public void countTest() {
        try {
            int total = userInfoDao.userCount();
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest() {
        try {
            int total = userInfoDao.userDelete(6);
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyTest() {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(6);
            userInfo.setCardId("1234");
            userInfo.setName("张三");
            userInfo.setDepartment("123212");
            int total = userInfoDao.userModify(userInfo);
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
