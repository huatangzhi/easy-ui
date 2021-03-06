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

    public List<UserInfo> getByPage(int start, int rows) throws Exception {
        String sql = "SELECT * FROM user_info ORDER BY department,card_id limit ? offset ? ";

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new Object[]{rows, start},
                new UserMapper());

        return userInfoList;
    }

    public List<UserInfo> getByCardId(String cardId) throws Exception {
        String sql = "SELECT * FROM user_info WHERE card_id=? ORDER BY id";

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new Object[]{cardId},
                new UserMapper());

        return userInfoList;
    }

    public UserInfo getById(Integer id) throws Exception {

        String sql = "SELECT * FROM user_info WHERE id=?";

        UserInfo userInfo = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new UserMapper());

        return userInfo;
    }

    public List<UserInfo> getByName(String name) throws Exception {
        String sql = "SELECT * FROM user_info WHERE name LIKE ? ORDER BY department,card_id";

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new Object[]{"%" + name + "%"},
                new UserMapper());

        return userInfoList;
    }

    public List<UserInfo> getByDepart(String depart) throws Exception {
        String sql = "SELECT * FROM user_info WHERE department LIKE ? ORDER BY department,card_id";

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new Object[]{"%" + depart + "%"},
                new UserMapper());

        return userInfoList;
    }

    public List<UserInfo> getBySameId(boolean yes) throws Exception {
        String sqlNull = "SELECT * FROM user_info WHERE same_id IS NULL ORDER BY department,card_id;";
        String sqlNotNull = "SELECT * FROM user_info WHERE same_id IS NOT NULL ORDER BY card_id,department;";

        String sql = yes ? sqlNull : sqlNotNull;

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new UserMapper());

        return userInfoList;
    }

    public int updateDepart(String cardId, String depart) {
        String sql = "UPDATE user_info SET same_id = ? WHERE card_id=?";
        return jdbcTemplate.update(sql, depart, cardId);
    }

    public int updateDepart(String cardId) throws Exception {
        List<UserInfo> sameUsers = getByCardId(cardId);
        if (sameUsers != null && sameUsers.size() > 0) {
            String depart = null;
            if (sameUsers.size() > 1) {
                depart = sameUsers.stream().map(user -> user.getDepartment()).collect(Collectors.joining(","));
            }

            return updateDepart(cardId, depart);
        }
        return 0;
    }

    public int userCount() throws Exception {
        String sql = "SELECT count(*) FROM user_info";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    public int userDelete(int delId) throws Exception {
        String sql = "DELETE FROM user_info WHERE id=?";
        UserInfo userInfo = getById(delId);
        int result = jdbcTemplate.update(sql, delId);
        updateDepart(userInfo.getCardId());

        return result;
    }

    public int userAdd(UserInfo userInfo) throws Exception {
        String sql = "INSERT INTO user_info (card_id, name, department) VALUES (?,?,?)";
        int result = jdbcTemplate.update(sql, userInfo.getCardId().trim(),
                userInfo.getName().trim(), userInfo.getDepartment().trim());
        //更新sameId信息
        updateDepart(userInfo.getCardId());

        return result;
    }


    public int userModify(UserInfo userInfo) throws Exception {
        String sql = "UPDATE user_info SET card_id=?,name=?,department=? WHERE id=?";
        UserInfo user = getById(userInfo.getId());
        int result = jdbcTemplate.update(sql, userInfo.getCardId().trim(), userInfo.getName().trim(),
                userInfo.getDepartment().trim(), userInfo.getId());

        if (!user.getCardId().equals(userInfo.getCardId())) {
            updateDepart(user.getCardId());
            updateDepart(userInfo.getCardId());
        }

        return result;
    }
}
