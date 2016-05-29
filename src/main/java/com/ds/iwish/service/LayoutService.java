package com.ds.iwish.service;

import com.ds.iwish.bean.Layout;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Template;
import com.ds.iwish.data.repository.LayoutRepository;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.manager.LayoutManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LayoutService {

    private LayoutManager layoutManager;
    private LayoutRepository layoutRepository;


    public String createLayout(Layout newLayout) {
        String result = getLayoutManager().validateLayoutForUpdate(newLayout);
        if (result != null) {
            return result;
        }
        Layout layout = getLayoutRepository().createLayout(newLayout);
        newLayout.setLayoutId(layout.getLayoutId());
        return null;
    }

    public List<Layout> getLayouts(){
        Profile profile = ProfileHelper.getProfileFromSession();
        List<Layout> layouts = getLayoutRepository().getLayoutsByUserId(profile.getId());
        return layouts == null ? Collections.emptyList() : layouts;
    }

    public Layout getLayout(long layoutId){
        return getLayoutRepository().getLayoutById(layoutId);
    }

    public String updateLayout(Layout layout) {
        String result = getLayoutManager().validateLayoutForUpdate(layout);
        if (result != null) {
            return result;
        }
        getLayoutRepository().updateLayout(layout);
        return null;
    }
    
    public void deleteLayout(long layoutId) {
        getLayoutRepository().deleteLayout(layoutId);
    }



    public LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Autowired
    public void setLayoutManager(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public LayoutRepository getLayoutRepository() {
        return layoutRepository;
    }

    @Autowired
    public void setLayoutRepository(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }
}
