package com.superbool.easyui.dao;

import com.superbool.easyui.model.PageBean;
import com.superbool.easyui.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class UserInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserInfo> userList(int page, int rows) throws Exception {
        String sql = "select * from user_info limit ? offset ?";
        List<UserInfo> userInfoList = this.jdbcTemplate.query(
                sql,
                new Object[]{rows, page},
                new RowMapper<UserInfo>() {
                    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        UserInfo userInfo = new UserInfo();
                        userInfo.setId(rs.getInt(1));
                        userInfo.setCardId(rs.getString(2));
                        userInfo.setName(rs.getString(3));
                        userInfo.setDepartment(rs.getString(4));
                        userInfo.setSameId(rs.getString(5));
                        return userInfo;
                    }
                });
        return userInfoList;
    }

    public int userCount() throws Exception {
        String sql = "select count(*) from user_info";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int userDelete(int delId) throws Exception {
        String sql = "delete from user_info where id=%s";
      /*  PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, delId);
        return pstmt.executeUpdate();*/
        return 0;
    }

    public int userAdd(UserInfo userInfo) throws Exception {
        String sql = "insert into user_info (card_id, name, department) values(?,?,?)";
        return jdbcTemplate.update(sql, userInfo.getCardId(), userInfo.getName(), userInfo.getDepartment());

    }

    public int userModify(UserInfo userInfo) throws Exception {
        String sql = "update user_info set name=?,phone=?,email=?,qq=? where id=?";
 /*       PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, userInfo.getCardId());
        pstmt.setString(2, userInfo.getName());
        pstmt.setString(3, userInfo.getDepartment());
        pstmt.setString(4, userInfo.getSameId());
        pstmt.setInt(5, userInfo.getId());
        return pstmt.executeUpdate();*/
        return 0;
    }
}
