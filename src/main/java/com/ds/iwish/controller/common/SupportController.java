package com.ds.iwish.controller.common;

import com.ds.iwish.bean.*;
import com.ds.iwish.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class SupportController {

    private WkuService wkuService;
    private CategoryService categoryService;
    private WishlistService wishlistService;
    private GiftlistService giftlistService;
    private LayoutService layoutService;
    private TemplateService templateService;


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

    protected void populateContent(ModelAndView modelAndView) {
        if (modelAndView.getModel().containsKey("currentCategory")) {
            Category category = (Category) modelAndView.getModel().get("currentCategory");
            Layout layout = getLayoutService().getLayout(category.getLayoutId());
            List<Wku> content = getWkuService().getWkusByCategory(category.getCategoryId());
            populateModel(modelAndView, layout, content);
        } else if (modelAndView.getModel().containsKey("currentWishlist")) {
            Wishlist wishlist = (Wishlist) modelAndView.getModel().get("currentWishlist");
            Layout layout = getLayoutService().getLayout(wishlist.getLayoutId());
            List<Wku> content = getWkuService().getWkusByWishlist(wishlist.getWishlistId());
            populateModel(modelAndView, layout, content);
        } else if (modelAndView.getModel().containsKey("currentGiftlist")) {
            Giftlist giftlist = (Giftlist) modelAndView.getModel().get("currentGiftlist");
            Layout layout = getLayoutService().getLayout(giftlist.getLayoutId());
            List<Wku> content = getWkuService().getWkusByGiftlist(giftlist.getWishlistId());
            populateModel(modelAndView, layout, content);
        }
    }

    protected void populateModel(ModelAndView modelAndView, Layout layout, List<Wku> content) {
        populateTemplatesPerWku(content);
        modelAndView.addObject("content", content);
        modelAndView.addObject("layout", layout);
    }

    private void populateTemplatesPerWku(List<Wku> content) {
        for (Wku wku : content) {
            Template template = getTemplateService().getTemplate(wku.getTemplateId());
            wku.setTemplate(template);
        }
    }


    public WkuService getWkuService() {
        return wkuService;
    }

    @Autowired
    public void setWkuService(WkuService wkuService) {
        this.wkuService = wkuService;
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

    public LayoutService getLayoutService() {
        return layoutService;
    }

    @Autowired
    public void setLayoutService(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

    public TemplateService getTemplateService() {
        return templateService;
    }

    @Autowired
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
