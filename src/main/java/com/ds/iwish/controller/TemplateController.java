package com.ds.iwish.controller;

import com.ds.iwish.bean.Template;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TemplateController {

    public static final String VIEW_NAME__ADD_TEMPLATE = "add-template";
    public static final String VIEW_NAME__EDIT_TEMPLATE = "edit-template";

    public static final String ATTR__TEMPLATES = "templates";
    public static final String ATTR__ERROR_MESSAGE = "errorMessage";

    private static final String[] BORDER_TYPES = {
            "None",
            "Dotted",
            "Dashed",
            "Solid",
            "Double",
            "Groove",
            "Ridge",
            "Inset",
            "Outset"
    };
    private static final String[] BORDER_WIDTH_TYPES = {
            "Thin",
            "Average",
            "Thick"
    };
    private static final String[] TEXT_STYLES = {
            "Pure",
            "Bold",
            "Italic",
            "Bold and Italic"
    };
    private static final String[] TEXT_FONTS = {
            "Arial",
            "Arial Black",
            "Comic Sans MS",
            "Courier New",
            "Georgia1",
            "Impact",
            "Lucida Console",
            "Lucida Sans Unicode",
            "Palatino Linotype",
            "Palatino",
            "Tahoma",
            "Times New Roman",
            "Trebuchet MS1",
            "Verdana"
    };
    private static final String[] IMAGE_POSITIONS = {
            "Top-Left",
            "Top-Center",
            "Top-Right",
            "Center-Right",
            "Bottom-Right",
            "Bottom-Center",
            "Bottom-Left",
            "Center-Left",
            "Center"
    };

    private TemplateService templateService;


    @RequestMapping(value = "/workshop/add-template", method = RequestMethod.GET)
    public ModelAndView getAddTemplate(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        populateExistingTemplates(modelAndView);
        setupSelectsTypes(request);
        modelAndView.setViewName(VIEW_NAME__ADD_TEMPLATE);

        return modelAndView;
    }

    @RequestMapping(value = "/workshop/edit-template/{templateId}", method = RequestMethod.GET)
    public ModelAndView getEditTemplate(@PathVariable("templateId") long templateId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        populateExistingTemplates(modelAndView);
        setupSelectsTypes(request);
        Template templateToEdit = getTemplateService().getTemplate(templateId);
        request.setAttribute("template", templateToEdit);
        modelAndView.setViewName(VIEW_NAME__EDIT_TEMPLATE);

        return modelAndView;
    }

    private void populateExistingTemplates(ModelAndView modelAndView) {
        List<Template> templates = getTemplateService().getTemplates();
        modelAndView.addObject(ATTR__TEMPLATES, templates);
    }

    private void setupSelectsTypes(HttpServletRequest request) {
        request.setAttribute("borderTypes", BORDER_TYPES);
        request.setAttribute("borderWidthTypes", BORDER_WIDTH_TYPES);
        request.setAttribute("textStyles", TEXT_STYLES);
        request.setAttribute("textFonts", TEXT_FONTS);
        request.setAttribute("imagePositions", IMAGE_POSITIONS);
    }

    @RequestMapping(value = "/workshop/add-template", method = RequestMethod.POST)
    public ModelAndView postAddTemplate(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Template newTemplate = new Template();
        setupNewTemplate(newTemplate, request);
        String errorMessage = getTemplateService().createTemplate(newTemplate);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/workshop/edit-template/"
                    + newTemplate.getTemplateId() + "?success=1");
        } else {
            populateExistingTemplates(modelAndView);
            setupSelectsTypes(request);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__ADD_TEMPLATE);
        }

        return modelAndView;
    }


    private void setupNewTemplate(Template template, HttpServletRequest request) {
        template.setTitle(request.getParameter("title"));
        template.setMainColor(request.getParameter("mainColor"));
        template.setTitleColor(request.getParameter("titleColor"));
        template.setTextColor(request.getParameter("textColor"));
        template.setBorderColor(request.getParameter("borderColor"));
        template.setBorderColor(request.getParameter("borderColor"));
        template.setBorderType(request.getParameter("borderType"));
        template.setBorderWidth(request.getParameter("borderWidth"));
        template.setTitleStyle(request.getParameter("titleStyle"));
        template.setTitleFont(request.getParameter("titleFont"));
        template.setTextStyle(request.getParameter("textStyle"));
        template.setTextFont(request.getParameter("textFont"));
        template.setImagePosition(request.getParameter("imagePosition"));
        template.setUserId(ProfileHelper.getProfileFromSession().getId());
    }

    @RequestMapping(value = "/workshop/edit-template", method = RequestMethod.POST)
    public ModelAndView postEditTemplate(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Template template = new Template();
        setupExistingTemplate(template, request);
        String errorMessage = getTemplateService().updateTemplate(template);
        if (errorMessage == null) {
            modelAndView.setViewName("redirect:/workshop/edit-template/"
                    + template.getTemplateId() + "?success=1");
        } else {
            populateExistingTemplates(modelAndView);
            setupSelectsTypes(request);
            request.setAttribute("template", template);
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__EDIT_TEMPLATE);
        }

        return modelAndView;
    }

    private void setupExistingTemplate(Template template, HttpServletRequest request) {
        template.setTemplateId(Long.parseLong(request.getParameter("templateId")));
        setupNewTemplate(template, request);
    }

    @RequestMapping(value = "/workshop/rm-template/{removeTemplateId}", method = RequestMethod.GET)
    public ModelAndView postRmTemplate(@PathVariable("removeTemplateId") long removeTemplateId) {
        getTemplateService().deleteTemplate(removeTemplateId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/workshop/add-template");
        return modelAndView;
    }


    public TemplateService getTemplateService() {
        return templateService;
    }

    @Autowired
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
