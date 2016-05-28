package com.ds.iwish.data.mapper;


import com.ds.iwish.bean.Layout;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LayoutRowMapper implements RowMapper<Layout> {

    public static final String LAYOUT_COLUMN__LAYOUT_ID = "layout_id";
    public static final String LAYOUT_COLUMN__TITLE = "title";
    public static final String LAYOUT_COLUMN__MODEL = "model";
    public static final String LAYOUT_COLUMN__HEIGHT = "height";
    public static final String LAYOUT_COLUMN__WIDTH = "width";
    public static final String LAYOUT_COLUMN__PADDING = "padding";
    public static final String LAYOUT_COLUMN__MARGIN = "margin";
    public static final String LAYOUT_COLUMN__USER_ID = "user_id";


    @Override
    public Layout mapRow(ResultSet pResultSet, int pRowNumber) throws SQLException {
        Layout layout = new Layout();
        layout.setLayoutId(pResultSet.getLong(LAYOUT_COLUMN__LAYOUT_ID));
        layout.setTitle(pResultSet.getString(LAYOUT_COLUMN__TITLE));
        layout.setModel(pResultSet.getString(LAYOUT_COLUMN__MODEL));
        layout.setHeight(pResultSet.getString(LAYOUT_COLUMN__HEIGHT));
        layout.setWidth(pResultSet.getString(LAYOUT_COLUMN__WIDTH));
        layout.setPadding(pResultSet.getString(LAYOUT_COLUMN__PADDING));
        layout.setMargin(pResultSet.getString(LAYOUT_COLUMN__MARGIN));
        layout.setUserId(pResultSet.getLong(LAYOUT_COLUMN__USER_ID));
        return layout;
    }
}
