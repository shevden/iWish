package com.ds.iwish.data.dao;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.data.dao.generic.GenericDAO;
import com.ds.iwish.data.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides CRUD functionality in the context of {@code User} entity.
 */
@Repository
public class UserDAO extends GenericDAO {

    public static final String SQL_SELECT_FROM_USERS_BY_ID = "select * from i_user where user_id = ?";
    public static final String SQL_SELECT_FROM_USERS_BY_EMAIL = "select * from i_user where email = ?";
    public static final String SQL_SELECT_FROM_USERS_BY_NAME_WITH_LIMIT = "select * from i_user where first_name like ? limit ?, ?";
    public static final String SQL_INSERT_INTO_USERS = "insert into i_user (password_hash, email, first_name, last_name) values (?, ?, ?, ?)";
    public static final String SQL_UPDATE_USERS_BY_ID = "update i_user set password_hash=?, email=?, first_name=?, last_name=? where user_id = ?";
    public static final String SQL_DELETE_FROM_USERS_BY_ID = "delete from i_user where user_id = ?";


    private JdbcTemplate mJdbcTemplate;


    /**
     * Returns {@code User} with specified id or
     * {@code null} if there is no such {@code User}.
     */
    public Profile getUserById(long id) {
        List<Profile> users = mJdbcTemplate.query(SQL_SELECT_FROM_USERS_BY_ID, new Object[]{id}, new UserRowMapper());
        return users.size() == 1? users.get(0): null;
    }

    /**
     * Returns {@code User} with specified email or
     * {@code null} if there is no such {@code User}.
     */
    public Profile getUserByEmail(String email) {
        List<Profile> users = mJdbcTemplate.query(SQL_SELECT_FROM_USERS_BY_EMAIL, new Object[]{email}, new UserRowMapper());
        return users.size() == 1? users.get(0): null;
    }

    public List<Profile> getUsersByName(String firstName, int pageSize, int pageNum) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_USERS_BY_NAME_WITH_LIMIT,
                new Object[]{
                        getStringForSqlLikeClause(firstName),
                        getIndexFrom(pageSize, pageNum),
                        pageSize
                },
                new UserRowMapper()
        );
    }

    public Profile createUser(final Profile user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        mJdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_USERS, new String[]{"user_id"});
                ps.setString(1, user.getPasswordHash());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public Profile updateUser(Profile user) {
        mJdbcTemplate.update(SQL_UPDATE_USERS_BY_ID, user.getPasswordHash(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

    public boolean deleteUser(long userId) {
        mJdbcTemplate.update(SQL_DELETE_FROM_USERS_BY_ID, userId);
        return true;
    }


    @Autowired
    public void setDataSource(DataSource dataSource) {
        mJdbcTemplate = new JdbcTemplate(dataSource);
    }
}
