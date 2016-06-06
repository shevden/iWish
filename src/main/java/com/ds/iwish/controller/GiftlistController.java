package com.ds.iwish.controller;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Giftlist;
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
import java.util.List;

@Controller
public class GiftlistController extends SupportController {

    public static final String VIEW_NAME__ADD_GIFTLIST = "add-giftlist";
    public static final String VIEW_NAME__EDIT_GIFTLIST = "edit-giftlist";

    public static final String ATTR__ERROR_MESSAGE = "errorMessage";

    private GiftlistService giftlistService;
    private CategoryService categoryService;
    private WishlistService wishlistService;


    @RequestMapping(value = "/catalog/view-giftlist/{giftlistId}", method = RequestMethod.GET)
    public ModelAndView getGiftlist(@PathVariable("giftlistId") long giftlistId) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        populateCurrentGiftlist(modelAndView, giftlistId);
        populateContent(modelAndView);
        modelAndView.setViewName("view-giftlist");
        return modelAndView;
    }

    private Giftlist populateCurrentGiftlist(ModelAndView modelAndView, long giftlistId){
        Giftlist giftlist = getGiftlistService().getGiftlist(giftlistId);
        modelAndView.addObject("currentGiftlist", giftlist);
        return giftlist;
    }

    @RequestMapping(value = "/catalog/add-giftlist", method = RequestMethod.GET)
    public ModelAndView getAddGiftlist() {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        setupLayouts(modelAndView);
        setupFriends(modelAndView);
        modelAndView.setViewName(VIEW_NAME__ADD_GIFTLIST);

        return modelAndView;
    }

    private void setupLayouts(ModelAndView modelAndView) {
        modelAndView.addObject("layouts", getGiftlistService().getLayouts());
    }

    private void setupFriends(ModelAndView modelAndView) {
        modelAndView.addObject("friends", getGiftlistService().getFriends());
    }


    @RequestMapping(value = "/catalog/edit-giftlist/{giftlistId}", method = RequestMethod.GET)
    public ModelAndView getEditGiftlist(@PathVariable("giftlistId") long giftlistId) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        setupLayouts(modelAndView);
        Giftlist giftlist = populateCurrentGiftlist(modelAndView, giftlistId);
        populateContent(modelAndView);
        populateFriendsForEdit(modelAndView, giftlist);
        modelAndView.setViewName(VIEW_NAME__EDIT_GIFTLIST);

        return modelAndView;
    }

    private void populateFriendsForEdit(ModelAndView modelAndView, Giftlist giftlist){
        List<Profile> friends = getGiftlistService().getFriends();
        friends.removeAll(giftlist.getFriends());
        modelAndView.addObject("friends", friends);
    }

    @RequestMapping(value = "/catalog/add-giftlist", method = RequestMethod.POST)
    public ModelAndView postAddGiftlist(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Giftlist newGiftlist = new Giftlist();
        setupNewGiftlist(newGiftlist, request);
        String errorMessage = getGiftlistService().createGiftlist(newGiftlist);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/catalog/edit-giftlist/"
                    + newGiftlist.getWishlistId() + "?success=1");
        } else {
            populateNavigationModel(modelAndView);
            setupLayouts(modelAndView);
            setupFriends(modelAndView);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__ADD_GIFTLIST);
        }

        return modelAndView;
    }

    private void setupNewGiftlist(Giftlist giftlist, HttpServletRequest request) {
        giftlist.setTitle(request.getParameter("title"));
        giftlist.setPriorityRaw(request.getParameter("priority"));
        giftlist.setBackground(request.getParameter("background"));
        giftlist.setColor(request.getParameter("color"));
        giftlist.setLayoutId(Long.parseLong(request.getParameter("layoutId")));
        giftlist.setUserId(ProfileHelper.getProfileFromSession().getId());
        String [] friendIds = request.getParameterValues("assignedFriends[]");
        if(friendIds == null) {
            return;
        }
        List<Profile> friends = getGiftlistService().getFriends();
        for(Profile friend : friends) {
            for (String friendId : friendIds) {
                if (friend.getId() == Long.parseLong(friendId)) {
                    giftlist.getFriends().add(friend);
                }
            }
        }
    }

    @RequestMapping(value = "/catalog/edit-giftlist", method = RequestMethod.POST)
    public ModelAndView postEditGiftlist(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Giftlist giftlist = new Giftlist();
        setupExistingGiftlist(giftlist, request);
        String errorMessage = getGiftlistService().updateGiftlist(giftlist);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/catalog/view-giftlist/" + giftlist.getWishlistId());
        } else {
            populateNavigationModel(modelAndView);
            setupLayouts(modelAndView);
            setupFriends(modelAndView);
            modelAndView.addObject("currentGiftlist", giftlist);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__EDIT_GIFTLIST);
        }

        return modelAndView;
    }

    private void setupExistingGiftlist(Giftlist giftlist, HttpServletRequest request) {
        giftlist.setWishlistId(Long.parseLong(request.getParameter("giftlistId")));
        setupNewGiftlist(giftlist, request);
    }

    @RequestMapping(value = "/catalog/rm-giftlist/{removeGiftlistId}", method = RequestMethod.GET)
    public ModelAndView postRmGiftlist(@PathVariable("removeGiftlistId") long removeGiftlistId) {
        getGiftlistService().deleteGiftlist(removeGiftlistId);
        ModelAndView modelAndView = new ModelAndView();
        Profile profile = ProfileHelper.getProfileFromSession();
        modelAndView.setViewName("redirect:/catalog/view-category/" + profile.getDefaultCategory());
        return modelAndView;
    }


    public GiftlistService getGiftlistService() {
        return giftlistService;
    }

    @Autowired
    public void setGiftlistService(GiftlistService giftlistService) {
        this.giftlistService = giftlistService;
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
}
