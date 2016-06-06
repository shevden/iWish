package com.ds.iwish.data.dao;


import com.ds.iwish.bean.Wishlist;
import com.ds.iwish.data.dao.generic.GenericDAO;
import com.ds.iwish.data.mapper.WishlistRowMapper;
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

@Repository
public class WishlistDAO extends GenericDAO {

    public static final String SQL_SELECT_FROM_WISHLIST_BY_ID = "select * from wishlist where wishlist_id = ?";
    public static final String SQL_SELECT_FROM_WISHLIST_BY_USER_ID = "select * from wishlist where user_id = ? " +
            "and is_public is null order by priority DESC";
    public static final String SQL_INSERT_INTO_WISHLIST = "insert into wishlist  (TITLE, PRIORITY, BACKGROUND, " +
            "COLOR, LAYOUT_ID, USER_ID) values(?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_WISHLIST_BY_ID = "update wishlist set TITLE = ?, PRIORITY = ?, BACKGROUND = ?, " +
            "COLOR = ?, LAYOUT_ID = ?, USER_ID = ? where wishlist_id = ?";
    public static final String SQL_DELETE_FROM_WISHLIST_BY_ID = "delete from wishlist where wishlist_id = ?";


    private JdbcTemplate mJdbcTemplate;


    public Wishlist getWishlistById(long wishlistId) {
        List<Wishlist> templates = mJdbcTemplate.query(SQL_SELECT_FROM_WISHLIST_BY_ID, new Object[]{wishlistId},
                new WishlistRowMapper());
        return templates.size() == 1? templates.get(0): null;
    }

    public List<Wishlist> getWishlistsByUserId(long userId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_WISHLIST_BY_USER_ID, new Object[]{userId},
                new WishlistRowMapper());
    }

    public Wishlist createWishlist(final Wishlist wishlist) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        mJdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_WISHLIST, new String[]{"wishlist_id"});
                ps.setString(1, wishlist.getTitle());
                ps.setLong(2, wishlist.getPriority());
                ps.setString(3, wishlist.getBackground());
                ps.setString(4, wishlist.getColor());
                ps.setLong(5, wishlist.getLayoutId());
                ps.setLong(6, wishlist.getUserId());
                return ps;
            }
        }, keyHolder);
        wishlist.setWishlistId(keyHolder.getKey().longValue());
        return wishlist;
    }

    public Wishlist updateWishlist(Wishlist wishlist) {
        mJdbcTemplate.update(SQL_UPDATE_WISHLIST_BY_ID,
                wishlist.getTitle(),
                wishlist.getPriority(),
                wishlist.getBackground(),
                wishlist.getColor(),
                wishlist.getLayoutId(),
                wishlist.getUserId(),
                wishlist.getWishlistId()
        );
        return wishlist;
    }

    public boolean deleteWishlist(long wishlistId) {
        mJdbcTemplate.update(SQL_DELETE_FROM_WISHLIST_BY_ID, wishlistId);
        return true;
    }


    protected JdbcTemplate getJdbcTemplate() {
        return mJdbcTemplate;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        mJdbcTemplate = new JdbcTemplate(dataSource);
    }
}
