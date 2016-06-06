package com.ds.iwish.controller;

import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Remote;
import com.ds.iwish.bean.Wku;
import com.ds.iwish.controller.common.SupportController;
import com.ds.iwish.helper.ImageHelper;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.helper.WebHelper;
import com.ds.iwish.service.TemplateService;
import com.ds.iwish.service.WkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WkuController extends SupportController {

    public static final String ATTR__ERROR_MESSAGE = "errorMessage";


    private WkuService wkuService;
    private TemplateService templateService;


    @RequestMapping(value = "/catalog/view-wku/{wkuId}", method = RequestMethod.GET)
    public ModelAndView getViewWku(@PathVariable("wkuId") long wkuId) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        populateCurrentWku(modelAndView, wkuId);
        modelAndView.setViewName("view-wish");
        return modelAndView;
    }

    private Wku populateCurrentWku(ModelAndView modelAndView, long wkuId){
        Wku wku = getWkuService().getWku(wkuId);
        modelAndView.addObject("currentWku", wku);
        return wku;
    }

    @RequestMapping(value = "/catalog/add-wku", method = RequestMethod.GET)
    public ModelAndView getAddWku(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        setupSelects(request);
        modelAndView.setViewName("add-wish");
        return modelAndView;
    }

    private void setupSelects(HttpServletRequest request) {
        request.setAttribute("templates", getTemplateService().getTemplates());
        request.setAttribute("categories", getCategoryService().getCategories());
        request.setAttribute("wishlists", getWishlistService().getWishlists());
        request.setAttribute("giftlists", getGiftlistService().getGiftlists());
    }

    @RequestMapping(value = "/catalog/edit-wku/{wkuId}", method = RequestMethod.GET)
    public ModelAndView getEditWku(@PathVariable("wkuId") long wkuId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        populateNavigationModel(modelAndView);
        Wku wku = populateCurrentWku(modelAndView, wkuId);
        populateUnusedCategories(modelAndView, wku);
        setupSelects(request);
        modelAndView.setViewName("edit-wish");
        return modelAndView;
    }

    private void populateUnusedCategories(ModelAndView modelAndView, Wku wku){
        List<Category> categories = getCategoryService().getCategories();
        categories.removeAll(wku.getCategories());
        modelAndView.addObject("unusedCategories", categories);
    }

    @RequestMapping(value = "/catalog/add-wku", method = RequestMethod.POST)
    public ModelAndView postAddWku(HttpServletRequest request,
                                   @RequestParam("largeImage") MultipartFile largeImage,
                                   @RequestParam("smallImage") MultipartFile smallImage) {
        ModelAndView modelAndView = new ModelAndView();

        Wku newWku = new Wku();
        setupNewWku(newWku, request);
        String errorMessage = getWkuService().createWku(newWku);
        if (errorMessage == null) {
            setupImages(largeImage, smallImage, newWku);
            modelAndView.setViewName("redirect:/catalog/view-wku/" + newWku.getWkuId());
        } else {
            populateNavigationModel(modelAndView);
            setupSelects(request);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName("add-wish");
        }

        return modelAndView;
    }

    private void setupNewWku(Wku wku, HttpServletRequest request) {
        wku.setTitle(request.getParameter("title"));
        wku.setPriorityRaw(request.getParameter("priority"));
        wku.setDescription(request.getParameter("description"));

        String templateIdRaw = request.getParameter("templateId");
        if (StringUtils.isNotEmpty(templateIdRaw)) {
            wku.setTemplateId(Long.parseLong(templateIdRaw));
        }
        String wishlistIdRaw = request.getParameter("wishlistId");
        if (StringUtils.isNotEmpty(wishlistIdRaw)) {
            wku.setWishlistId(Long.parseLong(wishlistIdRaw));
        }
        String giftlistIdRaw = request.getParameter("giftlistId");
        if (StringUtils.isNotEmpty(giftlistIdRaw)) {
            wku.setGiftlistId(Long.parseLong(giftlistIdRaw));
        }

        String [] links = request.getParameterValues("assignedLinks[]");
        wku.setRemotes(new ArrayList<>());
        if(links != null) {
            for (String link : links) {
                Remote remote = new Remote();
                remote.setRemoteUrl(link);
                wku.getRemotes().add(remote);
            }
        }

        String [] categoryIds = request.getParameterValues("assignedCategories[]");
        wku.setCategories(new ArrayList<>());
        if(categoryIds != null) {
            List<Category> categories = getCategoryService().getCategories();
            for (Category category : categories) {
                for (String categoryId : categoryIds) {
                    if (category.getCategoryId() == Long.parseLong(categoryId)) {
                        wku.getCategories().add(category);
                    }
                }
            }
        }
    }

    private void setupImages(@RequestParam("largeImage") MultipartFile largeImage, @RequestParam("smallImage") MultipartFile smallImage, Wku newWku) {
        String largeImageName = ImageHelper.getImageName(largeImage, newWku.getWkuId(), true);
        if (!largeImage.isEmpty() && largeImageName != null) {
            setupImage(largeImage, largeImageName);
            newWku.setLargeImageUrl(largeImageName);
        }
        String smallImageName = ImageHelper.getImageName(smallImage, newWku.getWkuId(), false);
        if (!smallImage.isEmpty() && smallImageName != null) {
            setupImage(smallImage, smallImageName);
            newWku.setSmallImageUrl(smallImageName);
        }
        getWkuService().updateWku(newWku);
    }

    private void setupImage(MultipartFile imagePart, String name) {
        String imageRepositoryPath = WebHelper.getRequest().getServletContext().getRealPath("repository/images/");
        try {
            byte[] bytes = imagePart.getBytes();
            File image = new File(imageRepositoryPath + File.separator + name);
            if (image.exists()) {
                if (!image.delete()) {
                    return;
                }
            }
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/catalog/edit-wku", method = RequestMethod.POST)
    public ModelAndView postEditWishlist(HttpServletRequest request,
                                         @RequestParam("largeImage") MultipartFile largeImage,
                                         @RequestParam("smallImage") MultipartFile smallImage) {
        ModelAndView modelAndView = new ModelAndView();
        long wkuId = Long.parseLong(request.getParameter("wkuId"));
        Wku wku = getWkuService().getWku(wkuId);
        setupExistingWku(wku, request);
        String errorMessage = getWkuService().updateWku(wku);
        if (errorMessage == null) {
            setupImages(largeImage, smallImage, wku);
            modelAndView.setViewName("redirect:/catalog/view-wku/" + wku.getWkuId());
        } else {
            populateNavigationModel(modelAndView);
            setupSelects(request);
            modelAndView.addObject("currentWku", wku);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName("edit-wish");
        }

        return modelAndView;
    }

    private void setupExistingWku(Wku wku, HttpServletRequest request) {
        wku.setWkuId(Long.parseLong(request.getParameter("wkuId")));
        setupNewWku(wku, request);
    }

    @RequestMapping(value = "/catalog/rm-wku/{removeWkuId}", method = RequestMethod.GET)
    public ModelAndView postRmWishlist(@PathVariable("removeWkuId") long removeWkuId) {
        getWkuService().deleteWku(removeWkuId);
        ModelAndView modelAndView = new ModelAndView();
        Profile profile = ProfileHelper.getProfileFromSession();
        modelAndView.setViewName("redirect:/catalog/view-category/" + profile.getDefaultCategory());
        return modelAndView;
    }


    public WkuService getWkuService() {
        return wkuService;
    }

    @Autowired
    public void setWkuService(WkuService wkuService) {
        this.wkuService = wkuService;
    }

    public TemplateService getTemplateService() {
        return templateService;
    }

    @Autowired
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
