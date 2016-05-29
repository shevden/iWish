package com.ds.iwish.data.mapper;


import com.ds.iwish.bean.Wishlist;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<Wishlist> {

    @Override
    public Wishlist mapRow(ResultSet pResultSet, int pRowNumber) throws SQLException {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistId(pResultSet.getLong("wishlist_id"));
        wishlist.setTitle(pResultSet.getString("title"));
        wishlist.setPriority(pResultSet.getInt("priority"));
        wishlist.setBackground(pResultSet.getString("background"));
        wishlist.setColor(pResultSet.getString("color"));
        wishlist.setLayoutId(pResultSet.getLong("layout_id"));
        wishlist.setUserId(pResultSet.getLong("user_id"));
        return wishlist;
    }
}
