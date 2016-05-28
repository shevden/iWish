package com.ds.iwish.data.dao;


import com.ds.iwish.bean.Template;
import com.ds.iwish.data.dao.generic.GenericDAO;
import com.ds.iwish.data.mapper.TemplateRowMapper;
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
public class TemplateDAO extends GenericDAO {

    public static final String SQL_SELECT_FROM_TEMPLATE_BY_ID = "select * from template where template_id = ?";
    public static final String SQL_SELECT_FROM_TEMPLATE_BY_USER_ID = "select * from template where user_id = ?";
    public static final String SQL_INSERT_INTO_TEMPLATE = "insert into TEMPLATE  (TITLE, MAIN_COLOR, " +
            "TITLE_COLOR, TEXT_COLOR, BORDER_COLOR, BORDER_TYPE, BORDER_WIDTH, TITLE_STYLE, " +
            "TITLE_FONT, TEXT_STYLE, TEXT_FONT, IMAGE_POSITION, USER_ID) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_TEMPLATE_BY_ID = "update TEMPLATE set TITLE = ?, MAIN_COLOR = ?, " +
            "TITLE_COLOR = ?, TEXT_COLOR = ?, BORDER_COLOR = ?, BORDER_TYPE = ?, BORDER_WIDTH = ?, TITLE_STYLE = ?, " +
            "TITLE_FONT = ?, TEXT_STYLE = ?, TEXT_FONT = ?, IMAGE_POSITION = ?, USER_ID = ? where TEMPLATE_ID = ?";
    public static final String SQL_DELETE_FROM_TEMPLATE_BY_ID = "delete from TEMPLATE where TEMPLATE_ID = ?";


    private JdbcTemplate mJdbcTemplate;


    public Template getTemplateById(long templateId) {
        List<Template> templates = mJdbcTemplate.query(SQL_SELECT_FROM_TEMPLATE_BY_ID, new Object[]{templateId},
                new TemplateRowMapper());
        return templates.size() == 1? templates.get(0): null;
    }

    public List<Template> getTemplatesByUserId(long userId) {
        return mJdbcTemplate.query(SQL_SELECT_FROM_TEMPLATE_BY_USER_ID, new Object[]{userId},
                new TemplateRowMapper());
    }

    public Template createTemplate(final Template template) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        mJdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_TEMPLATE, new String[]{"template_id"});
                ps.setString(1, template.getTitle());
                ps.setString(2, template.getMainColor());
                ps.setString(3, template.getTitleColor());
                ps.setString(4, template.getTextColor());
                ps.setString(5, template.getBorderColor());
                ps.setString(6, template.getBorderType());
                ps.setString(7, template.getBorderWidth());
                ps.setString(8, template.getTitleStyle());
                ps.setString(9, template.getTextFont());
                ps.setString(10, template.getTextStyle());
                ps.setString(11, template.getTextFont());
                ps.setString(12, template.getImagePosition());
                ps.setLong(13, template.getUserId());
                return ps;
            }
        }, keyHolder);
        template.setTemplateId(keyHolder.getKey().longValue());
        return template;
    }

    public Template updateTemplate(Template template) {
        mJdbcTemplate.update(SQL_UPDATE_TEMPLATE_BY_ID,
                template.getTitle(),
                template.getMainColor(),
                template.getTitleColor(),
                template.getTextColor(),
                template.getBorderColor(),
                template.getBorderType(),
                template.getBorderWidth(),
                template.getTitleStyle(),
                template.getTitleFont(),
                template.getTextStyle(),
                template.getTextFont(),
                template.getImagePosition(),
                template.getUserId(),
                template.getTemplateId());
        return template;
    }

    public boolean deleteTemplate(long templateId) {
        mJdbcTemplate.update(SQL_DELETE_FROM_TEMPLATE_BY_ID, templateId);
        return true;
    }


    @Autowired
    public void setDataSource(DataSource dataSource) {
        mJdbcTemplate = new JdbcTemplate(dataSource);
    }
}
