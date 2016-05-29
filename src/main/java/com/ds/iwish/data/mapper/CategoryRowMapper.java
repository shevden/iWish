package com.ds.iwish.data.mapper;


import com.ds.iwish.bean.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet pResultSet, int pRowNumber) throws SQLException {
        Category category = new Category();
        category.setCategoryId(pResultSet.getLong("category_id"));
        category.setTitle(pResultSet.getString("title"));
        category.setPriority(pResultSet.getInt("priority"));
        category.setBackground(pResultSet.getString("background"));
        category.setColor(pResultSet.getString("color"));
        category.setLayoutId(pResultSet.getLong("layout_id"));
        category.setUserId(pResultSet.getLong("user_id"));
        return category;
    }
}
