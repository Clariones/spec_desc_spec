package com.cla.sds.client;

import com.cla.sds.BaseEntity;
import com.cla.sds.CustomSdsCheckerManager;
import com.cla.sds.CustomSdsUserContextImpl;
import com.cla.sds.SdsUserContextImpl;
import com.cla.sds.pcclientpageview.PcClientLoginPage;
import com.cla.sds.secuser.SecUser;
import com.cla.sds.services.IamService;
import com.cla.sds.user.UserManagerImpl;
import com.cla.sds.userapp.UserApp;
import com.terapico.caf.Password;
import com.terapico.caf.baseelement.LoginParam;
import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.uccaf.BaseUserContext;
import com.terapico.utils.ListofUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientService extends CustomSdsCheckerManager {


    @Override
    public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
            throws IllegalAccessException {
        SdsUserContextImpl userContext = (SdsUserContextImpl) baseUserContext;
        IamService iamService = (IamService) userContext.getBean("iamService");
        Map<String, Object> loginInfo = iamService.getCachedLoginInfo(userContext);

        SecUser secUser = iamService.tryToLoadSecUser(userContext, loginInfo);
        UserApp userApp = iamService.tryToLoadUserApp(userContext, loginInfo);
        if (userApp != null) {
            userApp.setSecUser(secUser);
        }
        if (secUser == null) {
            iamService.onCheckAccessWhenAnonymousFound(userContext, loginInfo);
        }
        afterSecUserAppLoadedWhenCheckAccess(userContext, loginInfo, secUser, userApp);
        if (!isMethodNeedLogin(userContext, methodName, parameters)) {
            return accessOK();
        }

        return super.checkAccess(baseUserContext, methodName, parameters);
    }

    protected boolean isMethodNeedLogin(
            SdsUserContextImpl userContext, String methodName, Object[] parameters) {
        if (methodName.startsWith("customer")) {
            return true;
        }
        if (methodName.equals("test")) {
            return true;
        }
        return false;
    }

    protected void afterSecUserAppLoadedWhenCheckAccess(
            SdsUserContextImpl ctx, Map<String, Object> loginInfo, SecUser secUser, UserApp userApp) {
        // 默认加载userApp指定的对象到 currentUserInfo里. 这个逻辑不一定正确,项目上需要自己处理
        if (userApp == null) {
            return;
        }
        BaseEntity app = ctx.getDAOGroup().loadBasicData(userApp.getObjectType(), userApp.getObjectId());
        ((CustomSdsUserContextImpl) ctx).setCurrentUserInfo(app);
    }

    public Object clientLogin(SdsUserContextImpl ctx, LoginParam param) throws Exception {

        UserManagerImpl mng = (UserManagerImpl) ctx.getManagerGroup().getUserManager();

        if (param.getLoginMethod().equals(LoginParam.MOBILE_VCODE)) {
            return mng.loginByMobile(ctx, param.getMobile(), param.getVerifyCode());
        }

        if (param.getLoginMethod().equals(LoginParam.WECHAT_APP)) {
            return mng.loginByWechatMiniProgram(ctx, param.getCode());
        }

        if (param.getLoginMethod().equals(LoginParam.WECHAT_WORK_APP)) {
            return mng.loginByWechatWorkMiniProgram(ctx, param.getCode());
        }

        if (param.getLoginMethod().equals(LoginParam.ACCOUNT_PASSWORD)) {
            return mng.loginByPassword(ctx, param.getLogin(), new Password(param.getPassword()));
        }

        throw new Exception("自动生成的登录尚不支持" + param.getLoginMethod() + "方式登录");
    }

    @Override
    protected Object accessFail(String message) {
        return new PcClientLoginPage();
    }
}
