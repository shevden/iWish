package com.ds.iwish.data.repository;


import com.ds.iwish.bean.Wku;
import com.ds.iwish.data.dao.WkuDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WkuRepository {

    public static final Logger LOG = LogManager.getLogger(WkuRepository.class.getName());


    private WkuDAO wkuDAO;


    public Wku getWkuById(long pWkuId) {
        return getWkuDAO().getWkuById(pWkuId);
    }

    public List<Wku> getWkusByCategory(long categoryId) {
        return getWkuDAO().getWkusByCategory(categoryId);
    }

    public List<Wku> getWkusByWishlist(long wishlistId) {
        return getWkuDAO().getWkusByWishlist(wishlistId);
    }

    public List<Wku> getWkusByGiftlist(long giftlistId) {
        return getWkuDAO().getWkusByGiftlist(giftlistId);
    }

    public List<Wku> getWkusByKeyword(String keyword) {
        return getWkuDAO().getWkusByTitle(keyword);
    }

    public Wku createWku(Wku pWku) {
        LOG.info("Inside #createWku : (pWku = {})", pWku);
        Wku wku = getWkuDAO().createWku(pWku);
        LOG.info("Leaving #createWku : (template = {})", wku);
        return wku;
    }

    public Wku updateWku(Wku pWku) {
        return getWkuDAO().updateWku(pWku);
    }

    public boolean deleteWku(long pWkuId) {
        return getWkuDAO().deleteWku(pWkuId);
    }


    public WkuDAO getWkuDAO() {
        return wkuDAO;
    }

    @Autowired
    public void setWkuDAO(WkuDAO wkuDAO) {
        this.wkuDAO = wkuDAO;
    }
}
