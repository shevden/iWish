package com.ds.iwish.service;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Template;
import com.ds.iwish.data.repository.TemplateRepository;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.manager.TemplateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TemplateService {

    private TemplateManager templateManager;
    private TemplateRepository templateRepository;


    public String createTemplate(Template newTemplate) {
        String result = getTemplateManager().validateTemplateForUpdate(newTemplate);
        if (result != null) {
            return result;
        }
        Template template = getTemplateRepository().createTemplate(newTemplate);
        newTemplate.setTemplateId(template.getTemplateId());
        return null;
    }

    public List<Template> getTemplates(){
        Profile profile = ProfileHelper.getProfileFromSession();
        List<Template> templates = getTemplateRepository().getTemplatesByUserId(profile.getId());
        return templates == null ? Collections.emptyList() : templates;
    }

    public Template getTemplate(long templateId){
        return getTemplateRepository().getTemplateById(templateId);
    }

    public String updateTemplate(Template template) {
        String result = getTemplateManager().validateTemplateForUpdate(template);
        if (result != null) {
            return result;
        }
        getTemplateRepository().updateTemplate(template);
        return null;
    }

    public void deleteTemplate(long templateId) {
        getTemplateRepository().deleteTemplate(templateId);
    }


    public TemplateManager getTemplateManager() {
        return templateManager;
    }

    @Autowired
    public void setTemplateManager(TemplateManager templateManager) {
        this.templateManager = templateManager;
    }

    public TemplateRepository getTemplateRepository() {
        return templateRepository;
    }

    @Autowired
    public void setTemplateRepository(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }
}
