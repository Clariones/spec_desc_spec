package com.cla.sds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.cla.sds.tree.Node;
import com.terapico.caf.baseelement.CandidateQuery;
import com.terapico.utils.BaseCandidatesUtil;

public class SdsCandidatesUtil extends BaseCandidatesUtil{
	static {
		_for("candidate_container").usedIn("candidate_element").withRole("container");
		_for("candidate_container").isTree("false");
		_for("candidate_container").hasFields(";id;name;version;");
		_for("candidate_container").targetType("candidate_container");
		_for("sec_user").referTo("user_domain").withRole("domain");
		_for("sec_user").usedIn("login_history").withRole("sec_user");
		_for("sec_user").usedIn("wechat_miniapp_identity").withRole("sec_user");
		_for("sec_user").usedIn("wechat_workapp_identity").withRole("sec_user");
		_for("sec_user").usedIn("keypair_identity").withRole("sec_user");
		_for("sec_user").usedIn("user_app").withRole("sec_user");
		_for("sec_user").isTree("false");
		_for("sec_user").hasFields(";id;login;mobile;email;pwd;weixin_openid;weixin_appid;access_token;verification_code;verification_code_expire;last_login_time;version;");
		_for("sec_user").targetType("sec_user");
		_for("sec_user").anchorColumn("domain");
		_for("page_type").referTo("mobile_app").withRole("mobile_app");
		_for("page_type").usedIn("page").withRole("page_type");
		_for("page_type").isTree("false");
		_for("page_type").hasFields(";id;name;code;footer_tab;version;");
		_for("page_type").targetType("page_type");
		_for("page_type").anchorColumn("mobile_app");
		_for("project").referTo("company").withRole("company");
		_for("project").usedIn("page_content_spec").withRole("project");
		_for("project").usedIn("page_flow_spec").withRole("project");
		_for("project").usedIn("user_project").withRole("project");
		_for("project").usedIn("work_flow_spec").withRole("project");
		_for("project").usedIn("change_request_spec").withRole("project");
		_for("project").isTree("false");
		_for("project").hasFields(";id;name;version;");
		_for("project").targetType("project");
		_for("project").anchorColumn("company");
		_for("platform").usedIn("change_request_type").withRole("platform");
		_for("platform").usedIn("company").withRole("platform");
		_for("platform").usedIn("change_request").withRole("platform");
		_for("platform").isTree("false");
		_for("platform").hasFields(";id;name;create_time;last_update_time;version;");
		_for("platform").targetType("platform");
		_for("user_app").referTo("sec_user").withRole("sec_user");
		_for("user_app").usedIn("quick_link").withRole("app");
		_for("user_app").usedIn("list_access").withRole("app");
		_for("user_app").isTree("false");
		_for("user_app").hasFields(";id;title;app_icon;full_access;permission;object_type;object_id;location;version;");
		_for("user_app").targetType("user_app");
		_for("user_app").anchorColumn("sec_user");
		_for("change_request_type").referTo("platform").withRole("platform");
		_for("change_request_type").usedIn("change_request").withRole("request_type");
		_for("change_request_type").isTree("false");
		_for("change_request_type").hasFields(";id;name;code;icon;display_order;bind_types;step_configuration;version;");
		_for("change_request_type").targetType("change_request_type");
		_for("change_request_type").anchorColumn("platform");
		_for("user_domain").usedIn("user_white_list").withRole("domain");
		_for("user_domain").usedIn("sec_user").withRole("domain");
		_for("user_domain").usedIn("public_key_type").withRole("domain");
		_for("user_domain").isTree("false");
		_for("user_domain").hasFields(";id;name;version;");
		_for("user_domain").targetType("user_domain");
		_for("company").referTo("platform").withRole("platform");
		_for("company").usedIn("project").withRole("company");
		_for("company").usedIn("user").withRole("company");
		_for("company").isTree("false");
		_for("company").hasFields(";id;name;create_time;version;");
		_for("company").targetType("company");
		_for("company").anchorColumn("platform");
		_for("page").referTo("page_type").withRole("page_type");
		_for("page").referTo("mobile_app").withRole("mobile_app");
		_for("page").usedIn("ui_action").withRole("page");
		_for("page").usedIn("slide").withRole("page");
		_for("page").usedIn("section").withRole("page");
		_for("page").isTree("false");
		_for("page").hasFields(";id;page_title;link_to_url;display_order;version;");
		_for("page").targetType("page");
		_for("page").anchorColumn("page_type");
		_for("user").referTo("company").withRole("company");
		_for("user").usedIn("page_content_spec").withRole("owner");
		_for("user").usedIn("page_flow_spec").withRole("owner");
		_for("user").usedIn("user_project").withRole("user");
		_for("user").usedIn("work_flow_spec").withRole("owner");
		_for("user").usedIn("change_request_spec").withRole("owner");
		_for("user").isTree("false");
		_for("user").hasFields(";id;name;join_time;title;version;");
		_for("user").targetType("user");
		_for("user").anchorColumn("company");
		_for("public_key_type").referTo("user_domain").withRole("domain");
		_for("public_key_type").usedIn("keypair_identity").withRole("key_type");
		_for("public_key_type").isTree("false");
		_for("public_key_type").hasFields(";id;name;code;version;");
		_for("public_key_type").targetType("public_key_type");
		_for("public_key_type").anchorColumn("domain");
		_for("change_request").referTo("change_request_type").withRole("request_type");
		_for("change_request").referTo("platform").withRole("platform");
		_for("change_request").usedIn("event_update_profile").withRole("change_request");
		_for("change_request").isTree("false");
		_for("change_request").hasFields(";id;name;create_time;remote_ip;commited;version;");
		_for("change_request").targetType("change_request");
		_for("change_request").anchorColumn("request_type");
		_for("mobile_app").usedIn("page_type").withRole("mobile_app");
		_for("mobile_app").usedIn("page").withRole("mobile_app");
		_for("mobile_app").isTree("false");
		_for("mobile_app").hasFields(";id;name;version;");
		_for("mobile_app").targetType("mobile_app");

	}
	
	protected SdsBaseDAOImpl currentDAO = null;
	
	public Object queryCandidates(SdsUserContext userContext, CandidateQuery query) throws Exception {
		if (CandidateQuery.FOR_SEARCH.equals(query.getScenceCode())) {
			return queryCandidatesForSearch(userContext, query);
		}
		return queryCandidatesForAssign(userContext, query);
	}
	
	public Object queryCandidatesForAssign(SdsUserContext userContext, CandidateQuery query) throws Exception {
		query.setScenceCode(CandidateQuery.FOR_ASSIGN);
		query = prepareQueryInput(query);
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlForAssign(query, params);
		BaseCandidateEntity<? extends BaseEntity> candidates = executeQuery(userContext, query, sql, params);
		enhanceGroupByValues(userContext, query, candidates);
		return wrapperCandidates(userContext, candidates);
	}

	public Object queryCandidatesForSearch(SdsUserContext userContext, CandidateQuery query) throws Exception {
		query.setScenceCode(CandidateQuery.FOR_SEARCH);
		query = prepareQueryInput(query);
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlForSearch(query, params);
		BaseCandidateEntity<? extends BaseEntity> candidates = executeQuery(userContext, query, sql, params);
		enhanceGroupByValues(userContext, query, candidates);
		return wrapperCandidates(userContext, candidates);
	}
	
	protected Object wrapperCandidates(SdsUserContext userContext, BaseCandidateEntity<? extends BaseEntity> candidates) throws Exception {
		SmartList<BaseEntity> cs = (SmartList<BaseEntity>) candidates.getCandidates();
		
		List<Object> rList = new ArrayList<>();
		cs.forEach(v->{
			Map<String, Object> data = new HashMap<>();
			data.put("id",v.getId());
			data.put("name",v.getDisplayName());
			data.put("valuesOfGroupBy",v.valueByKey("valuesOfGroupBy"));
			rList.add(data);
		});
		return rList;
	}
	
	protected String normalizeModelName(String name) throws Exception {
		if (name == null) {
			return null;
		}
		if (!isValidFieldName(name)) {
			throw new Exception(name+"不是一个合法的字段名");
		}
		return new SdsNamingServiceDAO().mapToInternalColumn(name);
	}
	
	protected String getDisplayNameColumn(String typeName) {
		String displayNameColumn = SdsNamingServiceDAO.getDisplayNameColumnName(getJavaClassName(typeName));
		return displayNameColumn;
	}

	protected BaseCandidateEntity<? extends BaseEntity> executeQuery(SdsUserContext userContext, CandidateQuery query, String sql,
			List<Object> params) throws Exception{
		switch (query.getTargetType()) {
		case "platform":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getPlatformDAO();
			return userContext.getDAOGroup().getPlatformDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "company":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getCompanyDAO();
			return userContext.getDAOGroup().getCompanyDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "user":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getUserDAO();
			return userContext.getDAOGroup().getUserDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "change_request_type":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getChangeRequestTypeDAO();
			return userContext.getDAOGroup().getChangeRequestTypeDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "change_request":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getChangeRequestDAO();
			return userContext.getDAOGroup().getChangeRequestDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "project":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getProjectDAO();
			return userContext.getDAOGroup().getProjectDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "mobile_app":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getMobileAppDAO();
			return userContext.getDAOGroup().getMobileAppDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "page":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getPageDAO();
			return userContext.getDAOGroup().getPageDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "page_type":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getPageTypeDAO();
			return userContext.getDAOGroup().getPageTypeDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "user_domain":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getUserDomainDAO();
			return userContext.getDAOGroup().getUserDomainDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "sec_user":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getSecUserDAO();
			return userContext.getDAOGroup().getSecUserDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "user_app":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getUserAppDAO();
			return userContext.getDAOGroup().getUserAppDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "candidate_container":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getCandidateContainerDAO();
			return userContext.getDAOGroup().getCandidateContainerDAO().executeCandidatesQuery(query, sql, params.toArray());
		case "public_key_type":
			currentDAO = (SdsBaseDAOImpl)userContext.getDAOGroup().getPublicKeyTypeDAO();
			return userContext.getDAOGroup().getPublicKeyTypeDAO().executeCandidatesQuery(query, sql, params.toArray());

		default:
			throw new Exception("OOTB不支持"+query.getTargetType()+"的候选值查询");
		}
	}
	
	protected void enhanceGroupByValues(SdsUserContext userContext, CandidateQuery query,
			BaseCandidateEntity<? extends BaseEntity> candidates) throws Exception {
		if (query.getGroupBy() == null) {
			return; // 没有group by就直接返回
		}
		if (!isGroupByObject(query)) {
			// 用字段 group by
			for(BaseEntity cv : candidates.getCandidates()) {
				appendGroupByInfo(cv, String.valueOf(cv.propertyOf(this.getJavaMemberName(query.getGroupBy()))));
			}
			// 收集了数据就OK了
			return; 
		}
		
		List<BaseEntity> list = new ArrayList<>();
		for(BaseEntity cv : candidates.getCandidates()) {
			Object x = cv.propertyOf(this.getJavaMemberName(query.getGroupBy()));
			if (x instanceof BaseEntity) {
				list.add((BaseEntity) x);
			}
		}
		
		currentDAO.alias(list);
		if (!isGroupByTree(query)) {
			for(BaseEntity cv : candidates.getCandidates()) {
				Object x = cv.propertyOf(this.getJavaMemberName(query.getGroupBy()));
				if (x instanceof BaseEntity) {
					appendGroupByInfo(cv, ((BaseEntity) x).getDisplayName());
				}
			}
			return;
		}
		
		String gbTypeName = this.getGroupByTypeName(query);
		Map<String, String[]> groupByNames = new HashMap<>();
		for(BaseEntity cv : candidates.getCandidates()) {
			Object x = cv.propertyOf(this.getJavaMemberName(query.getGroupBy()));
			if (x instanceof BaseEntity) {
				if (groupByNames.containsKey(((BaseEntity) x).getId())) {
					appendGroupByInfo(cv, groupByNames.get(((BaseEntity) x).getId()));
					continue;
				}
				Node<BaseEntity> rootNode = userContext.getTreeService().loadAncestors(userContext, (BaseEntity) x);
				List<String> names = new ArrayList<>();
				rootNode.visit((node)->{
					BaseEntity value = node.value();
					if (value == null) {
						return;
					}
					names.add(String.valueOf(value.getDisplayName()));
				});
				names.add(String.valueOf(((BaseEntity) x).getDisplayName()));
				appendGroupByInfo(cv, names.toArray(new String[] {}));
				groupByNames.put(((BaseEntity) x).getId(),names.toArray(new String[] {}));
			}
		}
	}
	
	protected void appendGroupByInfo(BaseEntity cv, String ... groupBy) {
		cv.addItemToValueMap("valuesOfGroupBy", groupBy);
	}
}




















