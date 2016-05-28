package com.ds.iwish.data.mapper;

import com.ds.iwish.bean.Template;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateRowMapper implements RowMapper<Template> {

    public static final String TEMPLATE_COLUMN_ID = "template_id";
    public static final String TEMPLATE_COLUMN_TITLE = "title";
    public static final String TEMPLATE_COLUMN_MAIN_COLOR = "main_color";
    public static final String TEMPLATE_COLUMN_TITLE_COLOR = "title_color";
    public static final String TEMPLATE_COLUMN_TEXT_COLOR = "text_color";
    public static final String TEMPLATE_COLUMN_BORDER_COLOR = "border_color";
    public static final String TEMPLATE_COLUMN_BORDER_TYPE = "border_type";
    public static final String TEMPLATE_COLUMN_BORDER_WIDTH = "border_width";
    public static final String TEMPLATE_COLUMN_TITLE_STYLE = "title_style";
    public static final String TEMPLATE_COLUMN_TITLE_FONT = "title_font";
    public static final String TEMPLATE_COLUMN_TEXT_STYLE = "text_style";
    public static final String TEMPLATE_COLUMN_TEXT_FONT = "text_font";
    public static final String TEMPLATE_COLUMN_IMAGE_POSITION = "image_position";
    public static final String TEMPLATE_COLUMN__USER_ID = "user_id";


    @Override
    public Template mapRow(ResultSet pResultSet, int pRowNumber) throws SQLException {
        Template template = new Template();
        template.setTemplateId(pResultSet.getLong(TEMPLATE_COLUMN_ID));
        template.setTitle(pResultSet.getString(TEMPLATE_COLUMN_TITLE));
        template.setMainColor(pResultSet.getString(TEMPLATE_COLUMN_MAIN_COLOR));
        template.setTitleColor(pResultSet.getString(TEMPLATE_COLUMN_TITLE_COLOR));
        template.setTextColor(pResultSet.getString(TEMPLATE_COLUMN_TEXT_COLOR));
        template.setBorderColor(pResultSet.getString(TEMPLATE_COLUMN_BORDER_COLOR));
        template.setBorderType(pResultSet.getString(TEMPLATE_COLUMN_BORDER_TYPE));
        template.setBorderWidth(pResultSet.getString(TEMPLATE_COLUMN_BORDER_WIDTH));
        template.setTitleStyle(pResultSet.getString(TEMPLATE_COLUMN_TITLE_STYLE));
        template.setTitleFont(pResultSet.getString(TEMPLATE_COLUMN_TITLE_FONT));
        template.setTextStyle(pResultSet.getString(TEMPLATE_COLUMN_TEXT_STYLE));
        template.setTextFont(pResultSet.getString(TEMPLATE_COLUMN_TEXT_FONT));
        template.setImagePosition(pResultSet.getString(TEMPLATE_COLUMN_IMAGE_POSITION));
        template.setUserId(pResultSet.getLong(TEMPLATE_COLUMN__USER_ID));
        return template;
    }
}
