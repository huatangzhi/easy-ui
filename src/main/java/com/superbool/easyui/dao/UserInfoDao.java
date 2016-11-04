package com.superbool.easyui.dao;

import com.superbool.easyui.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Collectors;


@Repository
public class UserInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserInfo> userList(int start, int rows) throws Exception {
        String sql = "SELECT * FROM user_info GROUP BY department limit ? offset ? ";

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new Object[]{rows, start},
                new UserMapper());

        return userInfoList;
    }

    public List<UserInfo> getByCardId(String cardId) throws Exception {
        String sql = "SELECT * FROM user_info WHERE card_id=?";

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new Object[]{cardId},
                new UserMapper());

        return userInfoList;
    }

    public int userCount() throws Exception {
        String sql = "SELECT count(*) FROM user_info";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    public int userDelete(int delId) throws Exception {
        String sql = "DELETE FROM user_info WHERE id=?";
        return jdbcTemplate.update(sql, delId);
    }

    public int userAdd(UserInfo userInfo) throws Exception {
        String sql = "INSERT INTO user_info (card_id, name, department) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, userInfo.getCardId(), userInfo.getName(), userInfo.getDepartment());
    }

    public int userAddALL(UserInfo userInfo) throws Exception {
        List<UserInfo> sameUsers = getByCardId(userInfo.getCardId());
        String sameId = sameUsers.stream().map(user -> user.getId().toString()).collect(Collectors.joining(","));
        System.out.println(sameId);
        String sql = "INSERT INTO user_info (card_id, name, department,same_id) VALUES (?,?,?,?)";
        //return jdbcTemplate.update(sql, userInfo.getCardId(), userInfo.getName(), userInfo.getDepartment());
        return 0;
    }

    public int userModify(UserInfo userInfo) throws Exception {
        String sql = "UPDATE user_info SET card_id=?,name=?,department=?,same_id=? WHERE id=?";

        return jdbcTemplate.update(sql, userInfo.getCardId(), userInfo.getName(),
                userInfo.getDepartment(), userInfo.getSameId(), userInfo.getId());
    }
}
