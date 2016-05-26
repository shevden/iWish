package com.ds.iwish.helper;


import com.ds.iwish.bean.Profile;

import static com.ds.iwish.service.ProfileService.S_ATTR__PROFILE;

public class ProfileHelper {

    public static Profile getProfileFromSession(){
        return (Profile) WebHelper.getSession().getAttribute(S_ATTR__PROFILE);
    }
}
