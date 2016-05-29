package com.ds.iwish.data.dao;

import com.ds.iwish.bean.Layout;
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
import java.util.Collections;
import java.util.List;

/**
 * Provides CRUD functionality in the context of {@code User} entity.
 */
@Repository
public class UserDAO extends GenericDAO {

    public static final String SQL_SELECT_FROM_USERS_BY_ID = "select * from i_user where user_id = ?";
    public static final String SQL_SELECT_FROM_USERS_BY_EMAIL = "select * from i_user where email = ?";
    public static final String SQL_SELECT_FROM_USERS_NOT_FRIENDS = "select * from i_user f where (first_name like ? " +
            "or first_name like ? or last_name like ? or last_name like ?) and 0 = (select COUNT(*) from friend where " +
            "(user_id = ? and friend_id = f.user_id) or (friend_id = ? and user_id = f.user_id))";
    public static final String SQL_INSERT_INTO_FRIEND = "insert into friend (user_id, friend_id) values (?, ?)";
    public static final String SQL_SELECT_FROM_USERS_PENDING_ACTION = "select * from i_user where user_id in " +
            "(select user_id from friend where friend_id = ? and approved is null)";
    public static final String SQL_SELECT_FROM_USER_BY_FRIEND = "select * from i_user u where user_id in " +
            "(select user_id from friend where friend_id = ?) or " +
            "user_id in (select friend_id from friend where user_id = ?)";
    public static final String SQL_INSERT_INTO_USERS = "insert into i_user (password_hash, email, first_name, last_name) " +
            "values (?, ?, ?, ?)";
    public static final String SQL_UPDATE_USER_DEFAULTS = "update i_user set def_category_id = ?, " +
            "def_layout_id =?, def_template_id = ? where user_id = ?";
    public static final String SQL_UPDATE_FRIEND = "update friend set approved = ? where user_id = ? and friend_id = ?";
    public static final String SQL_DELETE_FROM_FRIEND = "delete from friend where (user_id = ? and friend_id = ?) or " +
            "(user_id = ? and friend_id = ?)";


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

    public List<Profile> getUsersByName(String firstName, String lastName, long userId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_USERS_NOT_FRIENDS,
                new Object[]{
                        getStringForSqlLikeClause(firstName),
                        getStringForSqlLikeClause(lastName),
                        getStringForSqlLikeClause(firstName),
                        getStringForSqlLikeClause(lastName),
                        userId,
                        userId
                },
                new UserRowMapper()
        );
    }

    public void createFriendship(long userId, long otherUserId) {
        mJdbcTemplate.update(SQL_INSERT_INTO_FRIEND, userId, otherUserId);
    }

    public List<Profile> getUnapprovedFriends(long profileId) {
        List<Profile> users = mJdbcTemplate.query(SQL_SELECT_FROM_USERS_PENDING_ACTION, new Object[]{profileId},
                new UserRowMapper());
        return users == null ? Collections.emptyList() : users;
    }

    public void updateFriendship(long userId, long otherUserId, boolean isApproved) {
        mJdbcTemplate.update(SQL_UPDATE_FRIEND, isApproved, otherUserId, userId);
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

    public void updateDefaults(Profile profile) {
        mJdbcTemplate.update(SQL_UPDATE_USER_DEFAULTS,
            profile.getDefaultCategory(),
            profile.getDefaultLayout(),
            profile.getDefaultTemplate(),
            profile.getId()
        );
    }

    public List<Profile> getFriendUsers(long userId) {
        List<Profile> users = mJdbcTemplate.query(SQL_SELECT_FROM_USER_BY_FRIEND, new Object[]{userId, userId},
                new UserRowMapper());
        return users == null ? Collections.emptyList() : users;
    }

    public boolean deleteFriendship(long userId, long otherUserId) {
        mJdbcTemplate.update(SQL_DELETE_FROM_FRIEND, userId, otherUserId, otherUserId, userId);
        return true;
    }


    @Autowired
    public void setDataSource(DataSource dataSource) {
        mJdbcTemplate = new JdbcTemplate(dataSource);
    }
}
