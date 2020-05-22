package com.cla.sds.pcclient;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cla.sds.SdsUserContext;
import com.cla.sds.SmartList;
import com.cla.sds.SdsBaseUtils;
import com.cla.sds.CustomSdsUserContextImpl;
import com.cla.sds.client.ClientService;
import com.cla.sds.pcclientpageview.*;

import com.terapico.utils.TextUtil;
import com.cla.sds.user.User;
import com.cla.sds.changerequestspec.ChangeRequestSpec;
import com.cla.sds.pagecontentspec.PageContentSpec;
import com.cla.sds.pageflowspec.PageFlowSpec;
import com.cla.sds.project.Project;
import com.cla.sds.user.User;
import com.cla.sds.userproject.UserProject;
import com.cla.sds.workflowspec.WorkFlowSpec;

/**
 * 此类负责：声明所有{@link PcClientViewService}中所使用的数据库搜索方法。 单独列出的目的是便于维护。
 * @author clariones
 *
 */
public abstract class PcClientDBQueryHelper{
	public static final Map<String, Object> EO = new HashMap<>();
	public int getPageSize(CustomSdsUserContextImpl ctx, String queryName) {
		return 20;
	}
	protected Map<String, Integer> toCountMap(List<Map<String, Object>> mapList) {
        Map<String, Integer> countMap = new HashMap<>();
        for(Map<String, Object> mapData : mapList) {
            String key = null;
            int num = 0;
            for(Object val: mapData.values()) {
                if (val instanceof String) {
                    key = (String) val;
                    continue;
                }
                if (val instanceof Number) {
                    num = ((Number) val).intValue();
                }
            }
            countMap.put(key, num);
        }
        return countMap;
    }
	@SuppressWarnings("serial")
	public <T> List<T> asList(T object) {
		return new ArrayList<T>() {{add(object);}};
	}
	/**
	 * 按ID查询用户,用于dashboard.
	 */
    public User findUserWhichByIdForDashboard( CustomSdsUserContextImpl ctx , String userId ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForFindUserWhichByIdForDashboard(ctx, params, userId);
		SmartList<User> list = ctx.getDAOGroup().getUserDAO().queryList(sql, params.toArray());
		if (list == null) {
			return null;
		}
		User result = list.first();
		enhanceUserWhichByIdForDashboard(ctx, result, "findUserWhichByIdForDashboard", userId);
		return result;
	}

	protected String prepareSqlAndParamsForFindUserWhichByIdForDashboard( CustomSdsUserContextImpl ctx, List<Object> params, String userId ) throws Exception {
		String sql = "SELECT DISTINCT T1.* from user_data T1 " +
			"    WHERE  (T1.id = ?) " +
			"    ORDER BY T1.id DESC " +
			"    LIMIT ? ";
		params.add(userId);
		params.add(1);
		return sql;
	}
	
	protected void enhanceUserWhichByIdForDashboard(CustomSdsUserContextImpl ctx, User data, String queryName, String userId ) throws Exception {
		if (data == null) {
			return;
		}
		List<UserProject> userProjectListListOfUserAsT1 = ctx.getDAOGroup().getUserDAO().loadOurUserProjectList(ctx,asList(data), EO);
		List<Project> projectListOfUserProjectAsT2 = SdsBaseUtils.collectReferencedObjectWithType(ctx, userProjectListListOfUserAsT1, Project.class);
		ctx.getDAOGroup().enhanceList(projectListOfUserProjectAsT2, Project.class);
		List<ChangeRequestSpec> changeRequestSpecListListOfProjectAsT3 = ctx.getDAOGroup().getProjectDAO().loadOurChangeRequestSpecList(ctx,projectListOfUserProjectAsT2, EO);
		List<WorkFlowSpec> workFlowSpecListListOfProjectAsT3 = ctx.getDAOGroup().getProjectDAO().loadOurWorkFlowSpecList(ctx,projectListOfUserProjectAsT2, EO);
		List<PageFlowSpec> pageFlowSpecListListOfProjectAsT3 = ctx.getDAOGroup().getProjectDAO().loadOurPageFlowSpecList(ctx,projectListOfUserProjectAsT2, EO);
		List<PageContentSpec> pageContentSpecListListOfProjectAsT3 = ctx.getDAOGroup().getProjectDAO().loadOurPageContentSpecList(ctx,projectListOfUserProjectAsT2, EO);
	}
	
	protected void enhanceUser(CustomSdsUserContextImpl ctx, User data, String queryName) throws Exception {
		// 默认什么都不增强. 需要额外增强请重载此函数
	}

}
