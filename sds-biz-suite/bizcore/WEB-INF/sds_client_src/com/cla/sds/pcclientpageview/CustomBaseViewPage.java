package com.cla.sds.pcclientpageview;

import com.cla.sds.pcclient.DBQuery;
import com.skynet.sql.core.DBQueryBuilder;
import com.terapico.caf.viewpage.SerializeScope;
import com.cla.sds.BaseViewPage;

// 这里写本项目中，所有view page的公共代码
public abstract class CustomBaseViewPage extends BaseViewPage{
    protected static DBQuery Q = new DBQuery();
}