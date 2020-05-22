package com.cla.sds.pcclient;

import com.cla.sds.iamservice.LoginMethod;
import com.terapico.caf.baseelement.LoginParam;
import com.terapico.utils.DebugUtil;
import com.terapico.utils.MapUtil;
import com.cla.sds.SdsUserContext;
import com.cla.sds.CustomSdsUserContextImpl;


/**
 * 此类负责：所有的业务逻辑，例如所有的过滤规则，计算规则
 * @author clariones
 *
 */
public class PcClientViewBizService extends BasicPcClientViewBizService{
    @Override
    protected int processRequestLogin(CustomSdsUserContextImpl ctx) throws Exception {
        LoginParam loginParam = new LoginParam();
        loginParam.setLogin(ctx.getLogin());
        loginParam.setPassword(ctx.getPassword());
        loginParam.setLoginMethod(LoginParam.ACCOUNT_PASSWORD);
        this.clientLogin(ctx, loginParam);
        return PRC_BY_DEFAULT;
    }
}
