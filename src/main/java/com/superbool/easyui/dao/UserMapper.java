package com.superbool.easyui.dao;

import com.superbool.easyui.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by superbool on 2016/11/4.
 */

public class UserMapper implements RowMapper<UserInfo> {

    @Override
    public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(rs.getInt(1));
        userInfo.setCardId(rs.getString(2));
        userInfo.setName(rs.getString(3));
        userInfo.setDepartment(rs.getString(4));
        userInfo.setSameId(rs.getString(5));
        return userInfo;
    }
}
