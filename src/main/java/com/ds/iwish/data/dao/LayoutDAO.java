package com.ds.iwish.data.dao;

import com.ds.iwish.bean.Layout;
import com.ds.iwish.data.dao.generic.GenericDAO;
import com.ds.iwish.data.mapper.LayoutRowMapper;
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
public class LayoutDAO extends GenericDAO {

    public static final String SQL_SELECT_FROM_LAYOUT_BY_ID = "select * from layout where layout_id = ?";
    public static final String SQL_SELECT_FROM_LAYOUT_BY_USER_ID = "select * from layout where user_id = ?";
    public static final String SQL_INSERT_INTO_LAYOUT = "insert into layout  (TITLE, MODEL, HEIGHT, WIDTH, " +
            "PADDING, MARGIN, USER_ID) values(?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_LAYOUT_BY_ID = "update layout set TITLE = ?, MODEL = ?,  " +
            "WIDTH = ?, HEIGHT = ?, PADDING = ?, MARGIN = ?, USER_ID = ? where layout_id = ?";
    public static final String SQL_DELETE_FROM_LAYOUT_BY_ID = "delete from layout where layout_id = ?";


    private JdbcTemplate mJdbcTemplate;


    public Layout getLayoutById(long layoutId) {
        List<Layout> templates = mJdbcTemplate.query(SQL_SELECT_FROM_LAYOUT_BY_ID, new Object[]{layoutId},
                new LayoutRowMapper());
        return templates.size() == 1? templates.get(0): null;
    }

    public List<Layout> getLayoutsByUserId(long userId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_LAYOUT_BY_USER_ID, new Object[]{userId},
                new LayoutRowMapper());
    }

    public Layout createLayout(final Layout layout) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        mJdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_LAYOUT, new String[]{"layout_id"});
                ps.setString(1, layout.getTitle());
                ps.setString(2, layout.getModel());
                ps.setString(3, layout.getHeight());
                ps.setString(4, layout.getWidth());
                ps.setString(5, layout.getPadding());
                ps.setString(6, layout.getMargin());
                ps.setLong(7, layout.getUserId());
                return ps;
            }
        }, keyHolder);
        layout.setLayoutId(keyHolder.getKey().longValue());
        return layout;
    }

    public Layout updateLayout(Layout layout) {
        mJdbcTemplate.update(SQL_UPDATE_LAYOUT_BY_ID,
                layout.getTitle(),
                layout.getModel(),
                layout.getWidth(),
                layout.getHeight(),
                layout.getPadding(),
                layout.getMargin(),
                layout.getUserId(),
                layout.getLayoutId()
        );
        return layout;
    }

    public boolean deleteLayout(long layoutId) {
        mJdbcTemplate.update(SQL_DELETE_FROM_LAYOUT_BY_ID, layoutId);
        return true;
    }


    @Autowired
    public void setDataSource(DataSource dataSource) {
        mJdbcTemplate = new JdbcTemplate(dataSource);
    }
}
