package com.ds.iwish.controller.common;

import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Giftlist;
import com.ds.iwish.bean.Wishlist;
import com.ds.iwish.service.CategoryService;
import com.ds.iwish.service.GiftlistService;
import com.ds.iwish.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class NavigationController {

    private CategoryService categoryService;
    private WishlistService wishlistService;
    private GiftlistService giftlistService;


    protected void populateNavigationModel(ModelAndView modelAndView) {
        populateExistingCategories(modelAndView);
        populateExistingWishlists(modelAndView);
        populateExistingGiftlists(modelAndView);
    }

    private void populateExistingCategories(ModelAndView modelAndView) {
        List<Category> categories = getCategoryService().getCategories();
        modelAndView.addObject("categories", categories);
    }

    private void populateExistingWishlists(ModelAndView modelAndView) {
        List<Wishlist> categories = getWishlistService().getWishlists();
        modelAndView.addObject("wishlists", categories);
    }

    private void populateExistingGiftlists(ModelAndView modelAndView) {
        List<Giftlist> categories = getGiftlistService().getGiftlists();
        modelAndView.addObject("giftlists", categories);
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
