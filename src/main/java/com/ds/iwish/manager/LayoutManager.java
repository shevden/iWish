package com.ds.iwish.manager;

import com.ds.iwish.bean.Layout;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class LayoutManager {

    public String validateLayoutForUpdate(Layout layout) {
        if (isNewLayoutFieldMissing(layout)) {
            return "Please, fulfil blank fields.";
        }
        return null;
    }

    private boolean isNewLayoutFieldMissing(Layout layout) {
        return StringUtils.isEmpty(layout.getTitle())
                || StringUtils.isEmpty(layout.getWidth())
                || StringUtils.isEmpty(layout.getHeight())
                || StringUtils.isEmpty(layout.getPadding())
                || StringUtils.isEmpty(layout.getMargin());
    }
}
