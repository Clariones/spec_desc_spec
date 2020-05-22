package com.cla.sds;

import com.cla.sds.pageflowspec.PageFlowSpec;

public class MiscUtils extends SdsBaseUtils{
    public static void appendLinkToUrl(BaseEntity entity, String linkToUrl) {
        entity.addItemToValueMap(SdsBaseConstants.X_LINK_TO_URL, linkToUrl);
    }
}
