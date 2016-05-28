package com.ds.iwish.manager;

import com.ds.iwish.bean.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TemplateManager {

    public String validateTemplateForUpdate(Template template) {
        if(isNewTemplateFieldMissing(template)) {
            return "Please, fulfil blank fields.";
        }
        return null;
    }

    private boolean isNewTemplateFieldMissing(Template template) {
        return StringUtils.isEmpty(template.getTitle());
    }
}
