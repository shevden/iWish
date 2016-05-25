package com.ds.iwish.data.mapper;


import com.ds.iwish.bean.Profile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper for {@code User} entity.
 *
 * @author
 */
public class UserRowMapper implements RowMapper<Profile> {

    public static final String USER_COLUMN_ID = "user_id";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD_HASH = "password_hash";
    public static final String USER_COLUMN_FIRST_NAME = "first_name";
    public static final String USER_COLUMN_LAST_NAME = "last_name";

    @Override
    public Profile mapRow(ResultSet pResultSet, int pRowNumber) throws SQLException {
        Profile user = new Profile();
        user.setId(pResultSet.getLong(USER_COLUMN_ID));
        user.setEmail(pResultSet.getString(USER_COLUMN_EMAIL));
        user.setPasswordHash(pResultSet.getString(USER_COLUMN_PASSWORD_HASH));
        user.setFirstName(pResultSet.getString(USER_COLUMN_FIRST_NAME));
        user.setLastName(pResultSet.getString(USER_COLUMN_LAST_NAME));
        return user;
    }

}