package com.ds.iwish.controller;

import com.ds.iwish.bean.Layout;
import com.ds.iwish.bean.Wku;
import com.ds.iwish.controller.common.SupportController;
import com.ds.iwish.helper.ProfileHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController extends SupportController {

    @RequestMapping(value = "/catalog/view-search", method = RequestMethod.GET)
    public ModelAndView getSearchResults(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        ModelAndView modelAndView = new ModelAndView();
        if (StringUtils.isNotEmpty(keyword)) {
            populateNavigationModel(modelAndView);
            populateCurrentSearchResults(modelAndView, keyword);
        }
        modelAndView.setViewName("search/view-search");
        return modelAndView;
    }

    private void populateCurrentSearchResults(ModelAndView modelAndView, String keyword){
        long layoutId = ProfileHelper.getProfileFromSession().getDefaultLayout();
        Layout layout = getLayoutService().getLayout(layoutId);
        List<Wku> content = getWkuService().getWkusByKeyword(keyword);
        populateModel(modelAndView, layout, content);
    }
}
