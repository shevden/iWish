package com.ds.iwish.data.mapper;


import com.ds.iwish.bean.Remote;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoteRowMapper implements RowMapper<Remote> {

    @Override
    public Remote mapRow(ResultSet pResultSet, int pRowNumber) throws SQLException {
        Remote remote = new Remote();
        remote.setWkuId(pResultSet.getLong("wku_id"));
        remote.setRemoteUrl(pResultSet.getString("remote_url"));
        return remote;
    }
}