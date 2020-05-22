package com.cla.sds.pcclient;

import com.cla.sds.pcclientpageview.SimpleToastPage;
import com.terapico.utils.DebugUtil;
import com.terapico.utils.MapUtil;
import com.terapico.utils.RandomUtil;
import com.terapico.utils.TextUtil;

import com.cla.sds.SdsUserContext;
import com.cla.sds.CustomSdsUserContextImpl;
import com.cla.sds.Footprint;
import com.cla.sds.FootprintProducer;

/**
 * 此类负责：所有的基础设施服务。 例如重复提交的基础方法，权限认证，异常包装，本服务内的通用工具方法等。
 * @author clariones
 *
 */
public abstract class BasicPcClientViewBizService extends PcClientViewService implements FootprintProducer {
	
	@Override
	public boolean canReplaceFootPrint(Footprint fp1, Footprint fp2) {
		if (!fp1.getMethodName().equals(fp2.getMethodName())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean clearTop() {
		return true;
	}
	
//	public Object sendVerifyCode(CustomSdsUserContextImpl ctx, String mobile) throws Exception {
//		mobile = TextUtil.formatChinaMobile(mobile);
//		if (mobile == null) {
//			throw new Exception("您输入的" + mobile + "不是有效的手机号");
//		}
//		String verifyCode = RandomUtil.randomNum(6);
//		cacheVerifyCode(ctx, mobile, verifyCode);
//		ctx.sendMessage(mobile, getSmsSign(ctx), getSmsVCodeTemplate(ctx), MapUtil.with("code", verifyCode));
//		if (ctx.isProductEnvironment()) {
//			ctx.setToast(makeToast("验证码已经发送到手机" + TextUtil.shrink(mobile, 3, 3, "***") + ",请注意查收", 5, "info"));
//		} else {
//			ctx.setToast(makeToast("验证码" + verifyCode + "已经发送到手机" + TextUtil.shrink(mobile, 3, 3, "***") + ",请注意查收", 5,
//					"info"));
//		}
//		SimpleToastPage page = this.assemblerSimpleToastPage(ctx, "sendVerifyCode");
//		return page.doRender(ctx);
//	}
}