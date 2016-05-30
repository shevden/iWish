package com.ds.iwish.data.repository;


import com.ds.iwish.bean.Giftlist;
import com.ds.iwish.data.dao.GiftlistDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftlistRepository {

    public static final Logger LOG = LogManager.getLogger(GiftlistRepository.class.getName());


    private GiftlistDAO giftlistDAO;


    public Giftlist getGiftlistById(long pGiftlistId) {
        return getGiftlistDAO().getGiftlistById(pGiftlistId);
    }

    public List<Giftlist> getGiftlistsByUserId(long pUserId) {
        return getGiftlistDAO().getGiftlistsByUserId(pUserId);
    }

    public Giftlist createGiftlist(Giftlist pGiftlist) {
        LOG.info("Inside #createGiftlist : (pGiftlist = {})", pGiftlist);
        Giftlist giftlist = getGiftlistDAO().createGiftlist(pGiftlist);
        LOG.info("Leaving #createGiftlist : (template = {})", giftlist);
        return giftlist;
    }

    public Giftlist updateGiftlist(Giftlist pGiftlist) {
        return getGiftlistDAO().updateGiftlist(pGiftlist);
    }

    public boolean deleteGiftlist(long pGiftlistId) {
        return getGiftlistDAO().deleteGiftlist(pGiftlistId);
    }


    public GiftlistDAO getGiftlistDAO() {
        return giftlistDAO;
    }

    @Autowired
    public void setGiftlistDAO(GiftlistDAO giftlistDAO) {
        this.giftlistDAO = giftlistDAO;
    }
}
