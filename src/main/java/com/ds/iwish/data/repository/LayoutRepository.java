package com.ds.iwish.data.repository;


import com.ds.iwish.bean.Layout;
import com.ds.iwish.data.dao.LayoutDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutRepository {

    public static final Logger LOG = LogManager.getLogger(LayoutRepository.class.getName());


    private LayoutDAO layoutDAO;


    public Layout getLayoutById(long pLayoutId) {
        LOG.info("Inside #getLayoutById : (pLayoutId = {})", pLayoutId);
        return getLayoutDAO().getLayoutById(pLayoutId);
    }

    public List<Layout> getLayoutsByUserId(long pUserId) {
        LOG.info("Inside #getLayoutsByUserId : (pUserId = {})", pUserId);
        return getLayoutDAO().getLayoutsByUserId(pUserId);
    }

    public Layout createLayout(Layout pLayout) {
        LOG.info("Inside #createLayout : (pLayout = {})", pLayout);
        Layout layout = getLayoutDAO().createLayout(pLayout);
        LOG.info("Leaving #createLayout : (layout = {})", layout);
        return layout;
    }

    public Layout updateLayout(Layout pLayout) {
        LOG.info("Inside #updateLayout : (pLayout = {})", pLayout);
        return getLayoutDAO().updateLayout(pLayout);
    }

    public boolean deleteLayout(long pLayoutId) {
        LOG.info("Inside #deleteLayout : (pLayoutId = {})", pLayoutId);
        return getLayoutDAO().deleteLayout(pLayoutId);
    }


    public LayoutDAO getLayoutDAO() {
        return layoutDAO;
    }

    @Autowired
    public void setLayoutDAO(LayoutDAO layoutDAO) {
        this.layoutDAO = layoutDAO;
    }
}
