package com.ds.iwish.manager;

import com.ds.iwish.bean.Category;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionContext;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager {

    public String validateCategoryForUpdate(Category category) {
        if (isNewCategoryFieldMissing(category)) {
            return "Please, fulfil blank fields.";
        }
        if(isTypeInvalid(category)){
            return "Priority value must be an integer from 1 to 100";
        }
        return null;
    }

    private boolean isNewCategoryFieldMissing(Category category) {
        return StringUtils.isEmpty(category.getTitle())
                || StringUtils.isEmpty(category.getPriorityRaw())
                || StringUtils.isEmpty(category.getBackground())
                || StringUtils.isEmpty(category.getColor());
    }

    private boolean isTypeInvalid(Category category) {
        try {
            int priority = Integer.parseInt(category.getPriorityRaw());
            return priority < 1 || priority > 100;
        } catch (Exception e) {
            return false;
        }
    }
}
