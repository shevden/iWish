package com.ds.iwish.data.mapper;

import com.ds.iwish.bean.Wku;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WkuRowMapper implements RowMapper<Wku> {

    @Override
    public Wku mapRow(ResultSet pResultSet, int pRowNumber) throws SQLException {
        Wku wku = new Wku();
        wku.setWkuId(pResultSet.getLong("wku_id"));
        wku.setTitle(pResultSet.getString("title"));
        wku.setPriority(pResultSet.getInt("priority"));
        wku.setDescription(pResultSet.getString("description"));
        wku.setSmallImageUrl(pResultSet.getString("small_image_url"));
        wku.setLargeImageUrl(pResultSet.getString("large_image_url"));
        wku.setTemplateId(pResultSet.getLong("template_id"));
        wku.setWishlistId(pResultSet.getLong("wishlist_id"));
        wku.setGiftlistId(pResultSet.getLong("giftlist_id"));

        return wku;
    }
}
