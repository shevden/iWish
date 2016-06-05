package com.ds.iwish.data.dao;


import com.ds.iwish.bean.Giftlist;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Wishlist;
import com.ds.iwish.data.mapper.WishlistRowMapper;
import com.ds.iwish.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GiftlistDAO {

    public static final String SQL_SELECT_FROM_WISHLIST_BY_USER_ID = "select * from wishlist where user_id = ? and is_public = 1";
    public static final String SQL_INSERT_INTO_WISHLIST = "insert into wishlist  (TITLE, PRIORITY, BACKGROUND, " +
            "COLOR, LAYOUT_ID, USER_ID, IS_PUBLIC) values(?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_SELECT_USER_FROM_GIFTLIST = "select friend_id from giftlist where wishlist_id = ?";

    public static final String SQL_INSERT_INTO_GIFTLIST = "insert into giftlist (wishlist_id, friend_id) values (?, ?)";
    public static final String SQL_DELETE_FROM_GIFTLIST = "delete from giftlist where wishlist_id = ?";


    private WishlistDAO wishlistDAO;
    private UserRepository userRepository;


    public Giftlist getGiftlistById(long wishlistId) {
        Wishlist wishlist = getWishlistDAO().getWishlistById(wishlistId);
        return getGiftlistByWishlist(wishlist);
    }

    private Giftlist getGiftlistByWishlist(Wishlist wishlist) {
        Giftlist giftlist = new Giftlist(wishlist);
        List<Long> friendIds = getFriendIds(giftlist);
        for (Long friendId : friendIds) {
            Profile profile = getUserRepository().getUserById(friendId);
            giftlist.getFriends().add(profile);
        }
        return giftlist;
    }

    private List<Long>  getFriendIds(Giftlist giftlist) {
        return getWishlistDAO().getJdbcTemplate().query(SQL_SELECT_USER_FROM_GIFTLIST,
                new Object[]{giftlist.getWishlistId()}, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getLong("friend_id");
            }
        });
    }

    public List<Giftlist> getGiftlistsByUserId(long userId) {
        List<Wishlist> wishlists = getWishlistDAO().getJdbcTemplate().query(SQL_SELECT_FROM_WISHLIST_BY_USER_ID,
                new Object[]{userId}, new WishlistRowMapper());
        List<Giftlist> giftlists = new ArrayList<>();
        for (Wishlist wishlist : wishlists) {
            Giftlist giftlist = getGiftlistByWishlist(wishlist);
            giftlists.add(giftlist);
        }
        return giftlists;
    }

    public Giftlist createGiftlist(final Giftlist giftlist) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getWishlistDAO().getJdbcTemplate().update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_WISHLIST, new String[]{"wishlist_id"});
                ps.setString(1, giftlist.getTitle());
                ps.setLong(2, giftlist.getPriority());
                ps.setString(3, giftlist.getBackground());
                ps.setString(4, giftlist.getColor());
                ps.setLong(5, giftlist.getLayoutId());
                ps.setLong(6, giftlist.getUserId());
                ps.setInt(7, 1);
                return ps;
            }
        }, keyHolder);
        giftlist.setWishlistId(keyHolder.getKey().longValue());
        createGiftlistFriends(giftlist);
        return giftlist;
    }

    private void createGiftlistFriends(Giftlist giftlist) {
        for (Profile profile : giftlist.getFriends()) {
            getWishlistDAO().getJdbcTemplate().update(SQL_INSERT_INTO_GIFTLIST,
                    giftlist.getWishlistId(), profile.getId());
        }
    }

    public Giftlist updateGiftlist(Giftlist giftlist) {
        Giftlist updated = (Giftlist) getWishlistDAO().updateWishlist(giftlist);
        getWishlistDAO().getJdbcTemplate().update(SQL_DELETE_FROM_GIFTLIST, updated.getWishlistId());
        createGiftlistFriends(giftlist);
        return updated;
    }

    public boolean deleteGiftlist(long giftlistId) {
        getWishlistDAO().getJdbcTemplate().update(SQL_DELETE_FROM_GIFTLIST, giftlistId);
        getWishlistDAO().deleteWishlist(giftlistId);
        return true;
    }


    public WishlistDAO getWishlistDAO() {
        return wishlistDAO;
    }

    @Autowired
    public void setWishlistDAO(WishlistDAO wishlistDAO) {
        this.wishlistDAO = wishlistDAO;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
