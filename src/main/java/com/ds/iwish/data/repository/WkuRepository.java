package com.ds.iwish.data.repository;


import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Remote;
import com.ds.iwish.bean.Wku;
import com.ds.iwish.data.dao.CategoryDAO;
import com.ds.iwish.data.dao.WkuDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WkuRepository {

    public static final Logger LOG = LogManager.getLogger(WkuRepository.class.getName());


    private WkuDAO wkuDAO;
    private CategoryDAO categoryDAO;

    public Wku getWkuById(long pWkuId) {
        LOG.info("Inside #getWkuById : (pWkuId = {})", pWkuId);
        Wku wku = getWkuDAO().getWkuById(pWkuId);
        if (wku == null) {
            return null;
        }
        List<Category> categories = getCategoryDAO().getCategoriesByWkuId(pWkuId);
        List<Remote> remotes = getWkuDAO().getRelatedRemotes(pWkuId);
        wku.setCategories(categories);
        wku.setRemotes(remotes);
        return wku;
    }

    public List<Wku> getWkusByCategory(long categoryId) {
        LOG.info("Inside #getWkusByCategory : (categoryId = {})", categoryId);
        return getWkuDAO().getWkusByCategory(categoryId);
    }

    public List<Wku> getWkusByWishlist(long wishlistId) {
        LOG.info("Inside #getWkusByWishlist : (wishlistId = {})", wishlistId);
        return getWkuDAO().getWkusByWishlist(wishlistId);
    }

    public List<Wku> getWkusByGiftlist(long giftlistId) {
        LOG.info("Inside #getWkusByGiftlist : (giftlistId = {})", giftlistId);
        return getWkuDAO().getWkusByGiftlist(giftlistId);
    }

    public List<Wku> getWkusByKeyword(String keyword) {
        LOG.info("Inside #getWkusByKeyword : (keyword = {})", keyword);
        List<Wku> wkus = getWkuDAO().getWkusByTitle(keyword);
        if (wkus.size() < 5) {
            List<Wku> otherWkus = getWkuDAO().getWkusByDescription(keyword);
            for (Wku otherWku : otherWkus) {
                if (!wkus.contains(otherWku)) {
                    wkus.add(otherWku);
                }
            }
        }
        return wkus;
    }

    public Wku createWku(Wku pWku) {
        LOG.info("Inside #createWku : (pWku = {})", pWku);
        Wku wku = getWkuDAO().createWku(pWku);
        LOG.info("Leaving #createWku : (wku = {})", wku);
        return wku;
    }

    public Wku updateWku(Wku pWku) {
        LOG.info("Inside #updateWku : (pWku = {})", pWku);
        return getWkuDAO().updateWku(pWku);
    }

    public boolean deleteWku(long pWkuId) {
        LOG.info("Inside #deleteWku : (pWkuId = {})", pWkuId);
        return getWkuDAO().deleteWku(pWkuId);
    }


    public WkuDAO getWkuDAO() {
        return wkuDAO;
    }

    @Autowired
    public void setWkuDAO(WkuDAO wkuDAO) {
        this.wkuDAO = wkuDAO;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
}
