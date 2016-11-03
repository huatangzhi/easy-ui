package com.superbool.easyui.dao;

import com.superbool.easyui.model.PageBean;
import com.superbool.easyui.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Repository
public class UserInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResultSet userList(PageBean pageBean) throws Exception {
        String sql = "select * from t_user limit ?,?";
        //PreparedStatement pstmt = jdbcTemplate.prepareStatement(sql);
        //pstmt.setInt(1, pageBean.getStart());
        //pstmt.setInt(2, pageBean.getRows());
        //return pstmt.executeQuery();
        return null;
    }

    public int userCount() throws Exception {
        String sql = "select count(*) as total from t_user";
        //PreparedStatement pstmt = connection.prepareStatement(sql);
        //ResultSet rs = pstmt.executeQuery();
        //if (rs.next()) {
        //    return rs.getInt("total");
        // } else {
        //     return 0;
        // }
        return 0;
    }

    public int userDelete(int delId) throws Exception {
        String sql = "delete from t_user where id=?";
      /*  PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, delId);
        return pstmt.executeUpdate();*/
        return 0;
    }

    public int userAdd(UserInfo userInfo) throws Exception {
/*        String sql = "insert into t_user values(null,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, userInfo.getCardId());
        pstmt.setString(2, userInfo.getName());
        pstmt.setString(3, userInfo.getDepartment());
        pstmt.setString(4, userInfo.getSameId());
        return pstmt.executeUpdate();*/
        return 0;
    }

    public int userModify(UserInfo userInfo) throws Exception {
/*        String sql = "update t_user set name=?,phone=?,email=?,qq=? where id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, userInfo.getCardId());
        pstmt.setString(2, userInfo.getName());
        pstmt.setString(3, userInfo.getDepartment());
        pstmt.setString(4, userInfo.getSameId());
        pstmt.setInt(5, userInfo.getId());
        return pstmt.executeUpdate();*/
        return 0;
    }
}
