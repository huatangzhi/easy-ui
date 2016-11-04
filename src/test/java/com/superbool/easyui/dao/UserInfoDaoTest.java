package com.superbool.easyui.dao;

import com.superbool.easyui.EasyUIApplication;
import com.superbool.easyui.model.PageBean;
import com.superbool.easyui.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
            List<UserInfo> userInfos = userInfoDao.userList(1, 3);
            System.out.println(userInfos);
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


}
