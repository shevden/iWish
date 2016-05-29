package com.ds.iwish.service;

import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Layout;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.data.repository.CategoryRepository;
import com.ds.iwish.data.repository.LayoutRepository;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.manager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {

    private CategoryManager categoryManager;
    private CategoryRepository categoryRepository;
    private LayoutRepository layoutRepository;


    public String createCategory(Category newCategory) {
        String result = getCategoryManager().validateCategoryForUpdate(newCategory);
        if (result != null) {
            return result;
        }
        newCategory.setPriority(Integer.parseInt(newCategory.getPriorityRaw()));
        Category category = getCategoryRepository().createCategory(newCategory);
        newCategory.setCategoryId(category.getCategoryId());
        return null;
    }

    public List<Category> getCategories(){
        Profile profile = ProfileHelper.getProfileFromSession();
        List<Category> categories = getCategoryRepository().getCategoriesByUserId(profile.getId());
        return categories == null ? Collections.emptyList() : categories;
    }

    public Category getCategory(long categoryId){
        return getCategoryRepository().getCategoryById(categoryId);
    }

    public String updateCategory(Category category) {
        String result = getCategoryManager().validateCategoryForUpdate(category);
        if (result != null) {
            return result;
        }
        category.setPriority(Integer.parseInt(category.getPriorityRaw()));
        getCategoryRepository().updateCategory(category);
        return null;
    }

    public void deleteCategory(long categoryId) {
        getCategoryRepository().deleteCategory(categoryId);
    }

    public List<Layout> getLayouts(){
        Profile profile = ProfileHelper.getProfileFromSession();
        return  getLayoutRepository().getLayoutsByUserId(profile.getId());
    }


    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    @Autowired
    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public LayoutRepository getLayoutRepository() {
        return layoutRepository;
    }

    @Autowired
    public void setLayoutRepository(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }
}
