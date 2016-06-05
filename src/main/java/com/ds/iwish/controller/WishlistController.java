package com.ds.iwish.controller;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Wishlist;
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
public class WishlistController extends SupportController {

    public static final String VIEW_NAME__ADD_WISHLIST = "add-wishlist";
    public static final String VIEW_NAME__EDIT_WISHLIST = "edit-wishlist";

    public static final String ATTR__ERROR_MESSAGE = "errorMessage";

    private WishlistService wishlistService;
    private CategoryService categoryService;
    private GiftlistService giftlistService;


    @RequestMapping(value = "/catalog/view-wishlist/{wishlistId}", method = RequestMethod.GET)
    public ModelAndView getWishlist(@PathVariable("wishlistId") long wishlistId) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        setupCurrentWishlist(modelAndView, wishlistId);
        modelAndView.setViewName("view-wishlist");
        return modelAndView;
    }

    private void setupCurrentWishlist(ModelAndView modelAndView, long wishlistId){
        Wishlist wishlist = getWishlistService().getWishlist(wishlistId);
        modelAndView.addObject("currentWishlist", wishlist);
    }

    @RequestMapping(value = "/catalog/add-wishlist", method = RequestMethod.GET)
    public ModelAndView getAddWishlist() {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        setupLayouts(modelAndView);
        modelAndView.setViewName(VIEW_NAME__ADD_WISHLIST);

        return modelAndView;
    }

    private void setupLayouts(ModelAndView modelAndView) {
        modelAndView.addObject("layouts", getWishlistService().getLayouts());
    }


    @RequestMapping(value = "/catalog/edit-wishlist/{wishlistId}", method = RequestMethod.GET)
    public ModelAndView getEditWishlist(@PathVariable("wishlistId") long wishlistId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        setupLayouts(modelAndView);
        setupCurrentWishlist(modelAndView, wishlistId);
        Wishlist wishlistToEdit = getWishlistService().getWishlist(wishlistId);
        request.setAttribute("wishlist", wishlistToEdit);
        modelAndView.setViewName(VIEW_NAME__EDIT_WISHLIST);

        return modelAndView;
    }

    @RequestMapping(value = "/catalog/add-wishlist", method = RequestMethod.POST)
    public ModelAndView postAddWishlist(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Wishlist newWishlist = new Wishlist();
        setupNewWishlist(newWishlist, request);
        String errorMessage = getWishlistService().createWishlist(newWishlist);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/catalog/edit-wishlist/"
                    + newWishlist.getWishlistId() + "?success=1");
        } else {
            populateNavigationModel(modelAndView);
            setupLayouts(modelAndView);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__ADD_WISHLIST);
        }

        return modelAndView;
    }

    private void setupNewWishlist(Wishlist wishlist, HttpServletRequest request) {
        wishlist.setTitle(request.getParameter("title"));
        wishlist.setPriorityRaw(request.getParameter("priority"));
        wishlist.setBackground(request.getParameter("background"));
        wishlist.setColor(request.getParameter("color"));
        wishlist.setLayoutId(Long.parseLong(request.getParameter("layoutId")));
        wishlist.setUserId(ProfileHelper.getProfileFromSession().getId());
    }

    @RequestMapping(value = "/catalog/edit-wishlist", method = RequestMethod.POST)
    public ModelAndView postEditWishlist(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Wishlist wishlist = new Wishlist();
        setupExistingWishlist(wishlist, request);
        String errorMessage = getWishlistService().updateWishlist(wishlist);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/catalog/view-wishlist/" + wishlist.getWishlistId());
        } else {
            populateNavigationModel(modelAndView);
            setupLayouts(modelAndView);
            modelAndView.addObject("currentWishlist", wishlist);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__EDIT_WISHLIST);
        }

        return modelAndView;
    }

    private void setupExistingWishlist(Wishlist wishlist, HttpServletRequest request) {
        wishlist.setWishlistId(Long.parseLong(request.getParameter("wishlistId")));
        setupNewWishlist(wishlist, request);
    }

    @RequestMapping(value = "/catalog/rm-wishlist/{removeWishlistId}", method = RequestMethod.GET)
    public ModelAndView postRmWishlist(@PathVariable("removeWishlistId") long removeWishlistId) {
        getWishlistService().deleteWishlist(removeWishlistId);
        ModelAndView modelAndView = new ModelAndView();
        Profile profile = ProfileHelper.getProfileFromSession();
        modelAndView.setViewName("redirect:/catalog/view-category/" + profile.getDefaultCategory());
        return modelAndView;
    }


    public WishlistService getWishlistService() {
        return wishlistService;
    }

    @Autowired
    public void setWishlistService(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public GiftlistService getGiftlistService() {
        return giftlistService;
    }

    @Autowired
    public void setGiftlistService(GiftlistService giftlistService) {
        this.giftlistService = giftlistService;
    }
}
