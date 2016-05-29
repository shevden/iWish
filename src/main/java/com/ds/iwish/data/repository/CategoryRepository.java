package com.ds.iwish.data.repository;


import com.ds.iwish.bean.Category;
import com.ds.iwish.data.dao.CategoryDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryRepository {

    public static final Logger LOG = LogManager.getLogger(CategoryRepository.class.getName());


    private CategoryDAO categoryDAO;


    public Category getCategoryById(long pCategoryId) {
        return getCategoryDAO().getCategoryById(pCategoryId);
    }

    public List<Category> getCategoriesByUserId(long pUserId) {
        return getCategoryDAO().getCategoriesByUserId(pUserId);
    }

    public Category createCategory(Category pCategory) {
        LOG.info("Inside #createCategory : (pCategory = {})", pCategory);
        Category category = getCategoryDAO().createCategory(pCategory);
        LOG.info("Leaving #createCategory : (template = {})", category);
        return category;
    }

    public Category updateCategory(Category pCategory) {
        return getCategoryDAO().updateCategory(pCategory);
    }

    public boolean deleteCategory(long pCategoryId) {
        return getCategoryDAO().deleteCategory(pCategoryId);
    }


    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
}
