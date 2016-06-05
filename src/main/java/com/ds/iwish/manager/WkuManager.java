package com.ds.iwish.manager;

import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Wku;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WkuManager {

    private CategoryService categoryService;

    public String validateWkuForUpdate(Wku wku) {
        if (isNewWkuFieldMissing(wku)) {
            return "Please, fulfil blank fields.";
        }
        return null;
    }

    private boolean isNewWkuFieldMissing(Wku wku) {
        return StringUtils.isEmpty(wku.getTitle());
    }

    public void setDefaults(Wku wku) {
        if (wku.getPriorityRaw() == null) {
            wku.setPriority(1);
        }
        if (wku.getDescription() == null) {
            wku.setDescription("");
        }
        if (wku.getTemplateId() == 0) {
            wku.setTemplateId(ProfileHelper.getProfileFromSession().getDefaultTemplate());
        }
        if (wku.getCategories().isEmpty()) {
            Category defaultCategory = getCategoryService().getCategory(
                    ProfileHelper.getProfileFromSession().getDefaultCategory()
            );
            wku.getCategories().add(defaultCategory);
        }
    }


    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
