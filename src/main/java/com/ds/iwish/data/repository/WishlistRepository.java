package com.ds.iwish.data.repository;


import com.ds.iwish.bean.Wishlist;
import com.ds.iwish.data.dao.WishlistDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistRepository {

    public static final Logger LOG = LogManager.getLogger(WishlistRepository.class.getName());


    private WishlistDAO wishlistDAO;


    public Wishlist getWishlistById(long pWishlistId) {
        LOG.info("Inside #getWishlistById : (pWishlistId = {})", pWishlistId);
        return getWishlistDAO().getWishlistById(pWishlistId);
    }

    public List<Wishlist> getWishlistsByUserId(long pUserId) {
        LOG.info("Inside #getWishlistsByUserId : (pUserId = {})", pUserId);
        return getWishlistDAO().getWishlistsByUserId(pUserId);
    }

    public Wishlist createWishlist(Wishlist pWishlist) {
        LOG.info("Inside #createWishlist : (pWishlist = {})", pWishlist);
        Wishlist wishlist = getWishlistDAO().createWishlist(pWishlist);
        LOG.info("Leaving #createWishlist : (wishlist = {})", wishlist);
        return wishlist;
    }

    public Wishlist updateWishlist(Wishlist pWishlist) {
        LOG.info("Inside #updateWishlist : (pWishlist = {})", pWishlist);
        return getWishlistDAO().updateWishlist(pWishlist);
    }

    public boolean deleteWishlist(long pWishlistId) {
        LOG.info("Inside #deleteWishlist : (pWishlistId = {})", pWishlistId);
        return getWishlistDAO().deleteWishlist(pWishlistId);
    }


    public WishlistDAO getWishlistDAO() {
        return wishlistDAO;
    }

    @Autowired
    public void setWishlistDAO(WishlistDAO wishlistDAO) {
        this.wishlistDAO = wishlistDAO;
    }
}
