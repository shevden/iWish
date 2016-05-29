package com.ds.iwish.manager;

import com.ds.iwish.bean.Wishlist;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class WishlistManager {

    public String validateWishlistForUpdate(Wishlist wishlist) {
        if (isNewWishlistFieldMissing(wishlist)) {
            return "Please, fulfil blank fields.";
        }
        if(isTypeInvalid(wishlist)){
            return "Priority value must be an integer from 1 to 100";
        }
        return null;
    }

    private boolean isNewWishlistFieldMissing(Wishlist wishlist) {
        return StringUtils.isEmpty(wishlist.getTitle())
                || StringUtils.isEmpty(wishlist.getBackground())
                || StringUtils.isEmpty(wishlist.getColor());
    }

    private boolean isTypeInvalid(Wishlist wishlist) {
        try {
            int priority = Integer.parseInt(wishlist.getPriorityRaw());
            return priority < 1 || priority > 100;
        } catch (Exception e) {
            return false;
        }
    }
}
