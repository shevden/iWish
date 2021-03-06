package com.ds.iwish.data.repository;


import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Template;
import com.ds.iwish.data.dao.TemplateDAO;
import com.ds.iwish.data.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateRepository {

    public static final Logger LOG = LogManager.getLogger(TemplateRepository.class.getName());


    private TemplateDAO templateDAO;


    public Template getTemplateById(long pId){
        LOG.info("Inside #getTemplateById : (pId = {})", pId);
        return getTemplateDAO().getTemplateById(pId);
    }

    public List<Template> getTemplatesByUserId(long pUserId){
        LOG.info("Inside #getTemplatesByUserId : (pUserId = {})", pUserId);
        return getTemplateDAO().getTemplatesByUserId(pUserId);
    }

    public Template createTemplate(Template pTemplate){
        LOG.info("Inside #createTemplate : (pTemplate = {})", pTemplate);
        Template template = getTemplateDAO().createTemplate(pTemplate);
        LOG.info("Leaving #createTemplate : (template = {})", template);
        return template;
    }

    public Template updateTemplate(Template pTemplate){
        LOG.info("Inside #updateTemplate : (pTemplate = {})", pTemplate);
        return getTemplateDAO().updateTemplate(pTemplate);
    }

    public boolean deleteTemplate(long pTemplateId){
        LOG.info("Inside #deleteTemplate : (pTemplateId = {})", pTemplateId);
        return getTemplateDAO().deleteTemplate(pTemplateId);
    }


    public TemplateDAO getTemplateDAO() {
        return templateDAO;
    }

    @Autowired
    public void setTemplateDAO(TemplateDAO templateDAO) {
        this.templateDAO = templateDAO;
    }
}
