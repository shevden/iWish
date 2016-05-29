package com.ds.iwish.data.dao;


import com.ds.iwish.bean.Category;
import com.ds.iwish.data.dao.generic.GenericDAO;
import com.ds.iwish.data.mapper.CategoryRowMapper;
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
public class CategoryDAO extends GenericDAO {

    public static final String SQL_SELECT_FROM_CATEGORY_BY_ID = "select * from category where category_id = ?";
    public static final String SQL_SELECT_FROM_CATEGORY_BY_USER_ID = "select * from category where user_id = ?";
    public static final String SQL_INSERT_INTO_CATEGORY = "insert into category  (TITLE, PRIORITY, BACKGROUND, " +
            "COLOR, LAYOUT_ID, USER_ID) values(?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_CATEGORY_BY_ID = "update category set TITLE = ?, PRIORITY = ?, BACKGROUND = ?, " +
            "COLOR = ?, LAYOUT_ID = ?, USER_ID = ? where category_id = ?";
    public static final String SQL_DELETE_FROM_CATEGORY_BY_ID = "delete from category where category_id = ?";


    private JdbcTemplate mJdbcTemplate;


    public Category getCategoryById(long categoryId) {
        List<Category> templates = mJdbcTemplate.query(SQL_SELECT_FROM_CATEGORY_BY_ID, new Object[]{categoryId},
                new CategoryRowMapper());
        return templates.size() == 1? templates.get(0): null;
    }

    public List<Category> getCategoriesByUserId(long userId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_CATEGORY_BY_USER_ID, new Object[]{userId},
                new CategoryRowMapper());
    }

    public Category createCategory(final Category category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        mJdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_CATEGORY, new String[]{"category_id"});
                ps.setString(1, category.getTitle());
                ps.setLong(2, category.getPriority());
                ps.setString(3, category.getBackground());
                ps.setString(4, category.getColor());
                ps.setLong(5, category.getLayoutId());
                ps.setLong(6, category.getUserId());
                return ps;
            }
        }, keyHolder);
        category.setCategoryId(keyHolder.getKey().longValue());
        return category;
    }

    public Category updateCategory(Category category) {
        mJdbcTemplate.update(SQL_UPDATE_CATEGORY_BY_ID,
                category.getTitle(),
                category.getPriority(),
                category.getBackground(),
                category.getColor(),
                category.getLayoutId(),
                category.getUserId(),
                category.getCategoryId()
        );
        return category;
    }

    public boolean deleteCategory(long categoryId) {
        mJdbcTemplate.update(SQL_DELETE_FROM_CATEGORY_BY_ID, categoryId);
        return true;
    }


    @Autowired
    public void setDataSource(DataSource dataSource) {
        mJdbcTemplate = new JdbcTemplate(dataSource);
    }
}
