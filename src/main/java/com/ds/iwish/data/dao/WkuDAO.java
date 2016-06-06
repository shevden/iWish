package com.ds.iwish.data.dao;

import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Remote;
import com.ds.iwish.bean.Wku;
import com.ds.iwish.data.dao.generic.GenericDAO;
import com.ds.iwish.data.mapper.RemoteRowMapper;
import com.ds.iwish.data.mapper.WkuRowMapper;
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
public class WkuDAO extends GenericDAO {

    public static final String SQL_SELECT_FROM_WKU_BY_ID = "select * from wku where wku_id = ?";
    public static final String SQL_SELECT_FROM_WKU_REMOTE = "select * from wku_remote where wku_id = ?";

    public static final String SQL_SELECT_FROM_WKU_BY_CATEGORY = "select * from wku where wku_id in " +
            "(select wku_id from wku_category where category_id = ?) order by priority DESC";
    public static final String SQL_SELECT_FROM_WKU_BY_WISHLIST = "select * from wku where wishlist_id = ? order by priority DESC";
    public static final String SQL_SELECT_FROM_WKU_BY_GIFTLIST = "select * from wku where giftlist_id = ? order by priority DESC";
    public static final String SQL_SELECT_FROM_WKU_BY_TITLE = "select * from wku where title like ? order by priority DESC";
    public static final String SQL_SELECT_FROM_WKU_BY_DESCRIPTION = "select * from wku where description like ? order by priority DESC";

    public static final String SQL_INSERT_INTO_WKU = "insert into wku  (TITLE, PRIORITY, LARGE_IMAGE_URL, " +
            "SMALL_IMAGE_URL, DESCRIPTION, TEMPLATE_ID, WISHLIST_ID, GIFTLIST_ID) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_WKU_BY_ID = "update wku set TITLE = ?, PRIORITY = ?, LARGE_IMAGE_URL = ?, " +
            "SMALL_IMAGE_URL = ?, DESCRIPTION = ?, TEMPLATE_ID = ?, WISHLIST_ID = ?, GIFTLIST_ID = ? where wku_id = ?";
    public static final String SQL_DELETE_FROM_WKU_BY_ID = "delete from wku where wku_id = ?";

    public static final String SQL_INSERT_INTO_WC = "insert into wku_category (wku_id, category_id) values (?, ?)";
    public static final String SQL_DELETE_FROM_WC = "delete from wku_category where wku_id = ?";

    public static final String SQL_INSERT_INTO_REMOTE = "insert into wku_remote (wku_id, remote_url) values (?, ?)";
    public static final String SQL_DELETE_FROM_REMOTE = "delete from wku_remote where wku_id = ?";


    private JdbcTemplate mJdbcTemplate;


    public Wku getWkuById(long wkuId) {
        List<Wku> templates = mJdbcTemplate.query(SQL_SELECT_FROM_WKU_BY_ID,
                new Object[]{wkuId},
                new WkuRowMapper());
        return templates.size() == 1? templates.get(0): null;
    }

    public List<Remote> getRelatedRemotes(long wkuId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_WKU_REMOTE,
                new Object[]{wkuId},
                new RemoteRowMapper());
    }

    public List<Wku> getWkusByCategory(long categoryId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_WKU_BY_CATEGORY,
                new Object[]{categoryId},
                new WkuRowMapper());
    }

    public List<Wku> getWkusByWishlist(long wishlistId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_WKU_BY_WISHLIST,
                new Object[]{wishlistId},
                new WkuRowMapper());
    }

    public List<Wku> getWkusByGiftlist(long wishlistId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_WKU_BY_GIFTLIST,
                new Object[]{wishlistId},
                new WkuRowMapper());
    }

    public List<Wku> getWkusByTitle(String keyword) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_WKU_BY_TITLE,
                new Object[]{getStringForSqlLikeClause(keyword)},
                new WkuRowMapper());
    }

    public List<Wku> getWkusByDescription(String keyword) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_WKU_BY_DESCRIPTION,
                new Object[]{getStringForSqlLikeClause(keyword)},
                new WkuRowMapper());
    }

    public Wku createWku(final Wku wku) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        mJdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_WKU, new String[]{"wku_id"});
                ps.setString(1, wku.getTitle());
                ps.setInt(2, wku.getPriority());
                ps.setString(3, wku.getLargeImageUrl());
                ps.setString(4, wku.getSmallImageUrl());
                ps.setString(5, wku.getDescription());
                ps.setLong(6, wku.getTemplateId());
                ps.setLong(7, wku.getWishlistId());
                ps.setLong(8, wku.getGiftlistId());
                return ps;
            }
        }, keyHolder);
        wku.setWkuId(keyHolder.getKey().longValue());
        createWC(wku);
        createRemotes(wku);
        return wku;
    }

    private void createWC(Wku wku) {
        for (Category category : wku.getCategories()) {
            mJdbcTemplate.update(SQL_INSERT_INTO_WC,
                    wku.getWkuId(), category.getCategoryId());
        }
    }

    private void createRemotes(Wku wku) {
        for (Remote remote : wku.getRemotes()) {
            mJdbcTemplate.update(SQL_INSERT_INTO_REMOTE,
                    wku.getWkuId(), remote.getRemoteUrl());
        }
    }

    public Wku updateWku(Wku wku) {
        mJdbcTemplate.update(SQL_DELETE_FROM_WC, wku.getWkuId());
        mJdbcTemplate.update(SQL_DELETE_FROM_REMOTE, wku.getWkuId());
        mJdbcTemplate.update(SQL_UPDATE_WKU_BY_ID,
                wku.getTitle(),
                wku.getPriority(),
                wku.getLargeImageUrl(),
                wku.getSmallImageUrl(),
                wku.getDescription(),
                wku.getTemplateId(),
                wku.getWishlistId(),
                wku.getGiftlistId(),

                wku.getWkuId()
        );
        createWC(wku);
        createRemotes(wku);
        return wku;
    }

    public boolean deleteWku(long wkuId) {
        mJdbcTemplate.update(SQL_DELETE_FROM_WC, wkuId);
        mJdbcTemplate.update(SQL_DELETE_FROM_REMOTE, wkuId);
        mJdbcTemplate.update(SQL_DELETE_FROM_WKU_BY_ID, wkuId);
        return true;
    }


    @Autowired
    public void setDataSource(DataSource dataSource) {
        mJdbcTemplate = new JdbcTemplate(dataSource);
    }
}
