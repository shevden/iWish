package com.ds.iwish.controller;

import com.ds.iwish.bean.Layout;
import com.ds.iwish.bean.Template;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LayoutController {

    public static final String VIEW_NAME__ADD_LAYOUT = "add-layout";
    public static final String VIEW_NAME__EDIT_LAYOUT = "edit-layout";

    public static final String ATTR__ERROR_MESSAGE = "errorMessage";

    private static final String[] MODELS = {
            "Grid",
            "List"
    };


    private LayoutService layoutService;


    @RequestMapping(value = "/workshop/add-layout", method = RequestMethod.GET)
    public ModelAndView getAddLayout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        populateExistingLayouts(modelAndView);
        setupSelectsTypes(request);
        modelAndView.setViewName(VIEW_NAME__ADD_LAYOUT);

        return modelAndView;
    }

    private void populateExistingLayouts(ModelAndView modelAndView) {
        List<Layout> layouts = getLayoutService().getLayouts();
        modelAndView.addObject("layouts", layouts);
    }

    private void setupSelectsTypes(HttpServletRequest request) {
        request.setAttribute("models", MODELS);
    }

    @RequestMapping(value = "/workshop/edit-layout/{layoutId}", method = RequestMethod.GET)
    public ModelAndView getEditLayout(@PathVariable("layoutId") long layoutId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        populateExistingLayouts(modelAndView);
        setupSelectsTypes(request);
        Layout layoutToEdit = getLayoutService().getLayout(layoutId);
        request.setAttribute("layout", layoutToEdit);
        modelAndView.setViewName(VIEW_NAME__EDIT_LAYOUT);

        return modelAndView;
    }
    
    @RequestMapping(value = "/workshop/add-layout", method = RequestMethod.POST)
    public ModelAndView postAddLayout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Layout newLayout = new Layout();
        setupNewLayout(newLayout, request);
        String errorMessage = getLayoutService().createLayout(newLayout);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/workshop/edit-layout/"
                    + newLayout.getLayoutId() + "?success=1");
        } else {
            populateExistingLayouts(modelAndView);
            setupSelectsTypes(request);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__ADD_LAYOUT);
        }

        return modelAndView;
    }

    private void setupNewLayout(Layout layout, HttpServletRequest request) {
        layout.setTitle(request.getParameter("title"));
        layout.setModel(request.getParameter("model"));
        layout.setWidth(request.getParameter("width"));
        layout.setHeight(request.getParameter("height"));
        layout.setPadding(request.getParameter("padding"));
        layout.setMargin(request.getParameter("margin"));
        layout.setUserId(ProfileHelper.getProfileFromSession().getId());
    }

    @RequestMapping(value = "/workshop/edit-layout", method = RequestMethod.POST)
    public ModelAndView postEditLayout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Layout layout = new Layout();
        setupExistingLayout(layout, request);
        String errorMessage = getLayoutService().updateLayout(layout);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/workshop/edit-layout/"
                    + layout.getLayoutId() + "?success=1");
        } else {
            populateExistingLayouts(modelAndView);
            setupSelectsTypes(request);
            request.setAttribute("layout", layout);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__EDIT_LAYOUT);
        }

        return modelAndView;
    }

    private void setupExistingLayout(Layout layout, HttpServletRequest request) {
        layout.setLayoutId(Long.parseLong(request.getParameter("layoutId")));
        setupNewLayout(layout, request);
    }

    @RequestMapping(value = "/workshop/rm-layout/{removeLayoutId}", method = RequestMethod.GET)
    public ModelAndView postRmLayout(@PathVariable("removeLayoutId") long removeLayoutId) {
        getLayoutService().deleteLayout(removeLayoutId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/workshop/add-layout");
        return modelAndView;
    }

    public LayoutService getLayoutService() {
        return layoutService;
    }

    @Autowired
    public void setLayoutService(LayoutService layoutService) {
        this.layoutService = layoutService;
    }
}
