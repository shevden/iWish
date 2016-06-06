package com.ds.iwish.service;

import com.ds.iwish.bean.Wku;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.data.repository.WkuRepository;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.manager.WkuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WkuService {

    private WkuManager wkuManager;
    private WkuRepository wkuRepository;


    public String createWku(Wku newWku) {
        String result = getWkuManager().validateWkuForUpdate(newWku);
        if (result != null) {
            return result;
        }
        try {
            int priority = Integer.parseInt(newWku.getPriorityRaw());
            newWku.setPriority(priority);
        } catch (NumberFormatException e) {
            newWku.setPriorityRaw(null);
        }
        getWkuManager().setDefaults(newWku);
        Wku wku = getWkuRepository().createWku(newWku);
        newWku.setWkuId(wku.getWkuId());
        return null;
    }

    public Wku getWku(long wkuId){
        return getWkuRepository().getWkuById(wkuId);
    }

    public List<Wku> getWkusByCategory(long categoryId){
        List<Wku> wkus = getWkuRepository().getWkusByCategory(categoryId);
        return wkus == null ? Collections.emptyList() : wkus;
    }

    public List<Wku> getWkusByWishlist(long wishlistId){
        List<Wku> wkus = getWkuRepository().getWkusByWishlist(wishlistId);
        return wkus == null ? Collections.emptyList() : wkus;
    }

    public List<Wku> getWkusByGiftlist(long giftlistId){
        List<Wku> wkus = getWkuRepository().getWkusByGiftlist(giftlistId);
        return wkus == null ? Collections.emptyList() : wkus;
    }

    public List<Wku> getWkusByKeyword(String keyword){
        List<Wku> wkus = getWkuRepository().getWkusByKeyword(keyword);
        return wkus == null ? Collections.emptyList() : wkus;
    }

    public String updateWku(Wku wku) {
        String result = getWkuManager().validateWkuForUpdate(wku);
        if (result != null) {
            return result;
        }
        try {
            int priority = Integer.parseInt(wku.getPriorityRaw());
            wku.setPriority(priority);
        } catch (NumberFormatException e) {
            wku.setPriorityRaw(null);
        }
        getWkuManager().setDefaults(wku);
        getWkuRepository().updateWku(wku);
        return null;
    }
    
    public void deleteWku(long wkuId) {
        getWkuRepository().deleteWku(wkuId);
    }



    public WkuManager getWkuManager() {
        return wkuManager;
    }

    @Autowired
    public void setWkuManager(WkuManager wkuManager) {
        this.wkuManager = wkuManager;
    }

    public WkuRepository getWkuRepository() {
        return wkuRepository;
    }

    @Autowired
    public void setWkuRepository(WkuRepository wkuRepository) {
        this.wkuRepository = wkuRepository;
    }
}
