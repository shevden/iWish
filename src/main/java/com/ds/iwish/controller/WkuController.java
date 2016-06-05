package com.ds.iwish.controller;

import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Remote;
import com.ds.iwish.bean.Wku;
import com.ds.iwish.controller.common.NavigationController;
import com.ds.iwish.helper.ImageHelper;
import com.ds.iwish.helper.WebHelper;
import com.ds.iwish.service.TemplateService;
import com.ds.iwish.service.WkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class WkuController extends NavigationController {

    public static final String ATTR__ERROR_MESSAGE = "errorMessage";


    private WkuService wkuService;
    private TemplateService templateService;


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

    @RequestMapping(value = "/catalog/add-wku", method = RequestMethod.POST)
    public ModelAndView postAddWku(HttpServletRequest request,
                                   @RequestParam("largeImage") MultipartFile largeImage,
                                   @RequestParam("smallImage") MultipartFile smallImage) {
        ModelAndView modelAndView = new ModelAndView();

        Wku newWku = new Wku();
        setupNewWku(newWku, request);
        String errorMessage = getWkuService().createWku(newWku);
        if (errorMessage == null) {
            String largeImageName = ImageHelper.getImageName(largeImage, newWku.getWkuId(), true);
            setupImage(largeImage, largeImageName);
            if (!largeImage.isEmpty()) {
                newWku.setLargeImageUrl(largeImageName);
            }
            String smallImageName = ImageHelper.getImageName(smallImage, newWku.getWkuId(), false);
            setupImage(smallImage, smallImageName);
            if (!smallImage.isEmpty()) {
                newWku.setSmallImageUrl(smallImageName);
            }
            getWkuService().updateWku(newWku);
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
        if(links != null) {
            for (String link : links) {
                Remote remote = new Remote();
                remote.setRemoteUrl(link);
                wku.getRemotes().add(remote);
            }
        }

        String [] categoryIds = request.getParameterValues("assignedCategories[]");
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

    private void setupImage(MultipartFile imagePart, String name) {
        String imageRepositoryPath = WebHelper.getRequest().getServletContext().getRealPath("repository/images/");
        if (imagePart.isEmpty()) {
            return;
        }
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
