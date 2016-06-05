package com.ds.iwish.controller;

import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.controller.common.SupportController;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.service.CategoryService;
import com.ds.iwish.service.GiftlistService;
import com.ds.iwish.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CategoryController extends SupportController {

    public static final String VIEW_NAME__ADD_CATEGORY = "add-category";
    public static final String VIEW_NAME__EDIT_CATEGORY = "edit-category";

    public static final String ATTR__ERROR_MESSAGE = "errorMessage";

    private CategoryService categoryService;
    private WishlistService wishlistService;
    private GiftlistService giftlistService;


    @RequestMapping(value = "/catalog/view-category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView getCategory(@PathVariable("categoryId") long categoryId) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        populateCurrentCategory(modelAndView, categoryId);
        populateContent(modelAndView);
        modelAndView.setViewName("view-category");
        return modelAndView;
    }

    private void populateCurrentCategory(ModelAndView modelAndView, long categoryId){
        Category category = getCategoryService().getCategory(categoryId);
        modelAndView.addObject("currentCategory", category);
    }

    @RequestMapping(value = "/catalog/add-category", method = RequestMethod.GET)
    public ModelAndView getAddCategory() {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        setupLayouts(modelAndView);
        modelAndView.setViewName(VIEW_NAME__ADD_CATEGORY);

        return modelAndView;
    }

    private void setupLayouts(ModelAndView modelAndView) {
        modelAndView.addObject("layouts", getCategoryService().getLayouts());
    }


    @RequestMapping(value = "/catalog/edit-category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView getEditCategory(@PathVariable("categoryId") long categoryId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        setupLayouts(modelAndView);
        populateCurrentCategory(modelAndView, categoryId);
        Category categoryToEdit = getCategoryService().getCategory(categoryId);
        request.setAttribute("category", categoryToEdit);
        modelAndView.setViewName(VIEW_NAME__EDIT_CATEGORY);

        return modelAndView;
    }

    @RequestMapping(value = "/catalog/add-category", method = RequestMethod.POST)
    public ModelAndView postAddCategory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Category newCategory = new Category();
        setupNewCategory(newCategory, request);
        String errorMessage = getCategoryService().createCategory(newCategory);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/catalog/edit-category/"
                    + newCategory.getCategoryId() + "?success=1");
        } else {
            populateNavigationModel(modelAndView);
            setupLayouts(modelAndView);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__ADD_CATEGORY);
        }

        return modelAndView;
    }

    private void setupNewCategory(Category category, HttpServletRequest request) {
        category.setTitle(request.getParameter("title"));
        category.setPriorityRaw(request.getParameter("priority"));
        category.setBackground(request.getParameter("background"));
        category.setColor(request.getParameter("color"));
        category.setLayoutId(Long.parseLong(request.getParameter("layoutId")));
        category.setUserId(ProfileHelper.getProfileFromSession().getId());
    }

    @RequestMapping(value = "/catalog/edit-category", method = RequestMethod.POST)
    public ModelAndView postEditCategory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Category category = new Category();
        setupExistingCategory(category, request);
        String errorMessage = getCategoryService().updateCategory(category);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/catalog/view-category/" + category.getCategoryId());
        } else {
            populateNavigationModel(modelAndView);
            setupLayouts(modelAndView);
            modelAndView.addObject("currentCategory", category);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__EDIT_CATEGORY);
        }

        return modelAndView;
    }

    private void setupExistingCategory(Category category, HttpServletRequest request) {
        category.setCategoryId(Long.parseLong(request.getParameter("categoryId")));
        setupNewCategory(category, request);
    }

    @RequestMapping(value = "/catalog/rm-category/{removeCategoryId}", method = RequestMethod.GET)
    public ModelAndView postRmCategory(@PathVariable("removeCategoryId") long removeCategoryId) {
        getCategoryService().deleteCategory(removeCategoryId);
        ModelAndView modelAndView = new ModelAndView();
        Profile profile = ProfileHelper.getProfileFromSession();
        modelAndView.setViewName("redirect:/catalog/view-category/" + profile.getDefaultCategory());
        return modelAndView;
    }


    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public WishlistService getWishlistService() {
        return wishlistService;
    }

    @Autowired
    public void setWishlistService(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    public GiftlistService getGiftlistService() {
        return giftlistService;
    }

    @Autowired
    public void setGiftlistService(GiftlistService giftlistService) {
        this.giftlistService = giftlistService;
    }
}
