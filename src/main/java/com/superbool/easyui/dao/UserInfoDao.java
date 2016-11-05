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
        String sql = "SELECT * FROM user_info GROUP BY department,id limit ? offset ? ";

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
        String sql = "SELECT * FROM user_info WHERE name LIKE ? ORDER BY id";

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new Object[]{"%" + name + "%"},
                new UserMapper());

        return userInfoList;
    }

    public List<UserInfo> getByDepart(String depart) throws Exception {
        String sql = "SELECT * FROM user_info WHERE department LIKE ? ORDER BY id";

        List<UserInfo> userInfoList = jdbcTemplate.query(
                sql,
                new Object[]{"%" + depart + "%"},
                new UserMapper());

        return userInfoList;
    }


    public int updateSameId(String cardId, String sameId) {
        String sql = "UPDATE user_info SET same_id = ? WHERE card_id=?";
        return jdbcTemplate.update(sql, sameId, cardId);
    }

    public int updateSameId(String cardId) throws Exception {
        List<UserInfo> sameUsers = getByCardId(cardId);
        if (sameUsers != null && sameUsers.size() > 0) {
            String sameId = null;
            if (sameUsers.size() > 1) {
                sameId = sameUsers.stream().map(user -> user.getId().toString()).collect(Collectors.joining(","));
            }
            return updateSameId(cardId, sameId);
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
        updateSameId(userInfo.getCardId());

        return result;
    }

    public int userAdd(UserInfo userInfo) throws Exception {
        String sql = "INSERT INTO user_info (card_id, name, department) VALUES (?,?,?)";
        int result = jdbcTemplate.update(sql, userInfo.getCardId(), userInfo.getName(), userInfo.getDepartment());
        //更新sameId信息
        updateSameId(userInfo.getCardId());

        return result;
    }


    public int userModify(UserInfo userInfo) throws Exception {
        String sql = "UPDATE user_info SET card_id=?,name=?,department=? WHERE id=?";
        UserInfo user = getById(userInfo.getId());
        int result = jdbcTemplate.update(sql, userInfo.getCardId(), userInfo.getName(),
                userInfo.getDepartment(), userInfo.getId());

        if (!user.getCardId().equals(userInfo.getCardId())) {
            updateSameId(user.getCardId());
            updateSameId(userInfo.getCardId());
        }

        return result;
    }
}
