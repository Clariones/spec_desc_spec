<%@page contentType="text/plain; charset=UTF-8"%><%@include file="javaobjhead.jsp"%><%String checkManagerClazz=oc.getNameCapitalizeFirst()+"CheckerManager";%>

@savefile:<%=coreSrcDestPath%>/com/<%=oc.getUniStr().getNameSpace()%>/<%=oc.getNameCapitalizeFirst().toLowerCase()%>/UserContext.java 
package com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>;

import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.Cookie;
import com.skynet.infrastructure.ESClient;
import com.terapico.uccaf.BaseUserContext;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;

import com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>.tree.TreeServiceImpl;

public interface UserContext extends BaseUserContext {
    public DateTime now();
    public String currentUserName();
    public void log(String message);
    public String reportExecution();
    public void putToCache(String key, Object value, int timeToLiveInSeconds);
    public void cacheUser(Object value);
    public Object userOf(Class<?> clazz);
    public <T> T getCachedObject(String key, Class<T> clazz);
    public void removeFromCache(String key);
    public void sendEmail(String to, String subject, String content) throws Exception;
    public String tokenId();
    public Object getBean(String beanName);
    public UserContext castTo(Class<UserContext> targetClass) throws Exception;
    public List<String[]> relationBetween(String sourceType, String sourceId, String targetType, String targetId);
    public void addAccessTokens(Map<String, Object> tokens);
    public Map<String, Object> getAccessTokens();
    public String getRemoteIP();
    public String getUserAgent();
    public String getPublicMediaServicePrefix();
    public void setPublicMediaServicePrefix(String publicMediaServicePrefix);
    public void sendMessage(String dest, String fromWho, String template, Map<String, String> parameters) throws Exception;
    public void setEsClient(ESClient esClient);
    public ESClient getEsClient();
    public DAOGroup getDAOGroup();
    public ManagerGroup getManagerGroup();
    public Map<String, Object> getRequestParameters();
    public Map<String, Object> getContextLocalStorage();
    public Object getFromContextLocalStorage(String key);
    public void putIntoContextLocalStorage(String key, Object value);
    public void setContextLocalStorage(Map<String, Object> contextLocalStorage);
    public String getRequestHeader(String name);
    public void setResponseHeader(String name, String value);
    public String getResponseHeadder(String name);
    public void forceResponseXClassHeader(String clazzName);
    public Cookie[] getRequestCookies();
    public <T> List<T> getCachedObjectsWithOneType(List<String> keys, Class<T> clazz);
    public TreeServiceImpl getTreeService();
    public String getCityByIp();
}

@savefile:<%=coreSrcDestPath%>/com/<%=oc.getUniStr().getNameSpace()%>/<%=oc.getNameCapitalizeFirst().toLowerCase()%>/UserContextImpl.java 
package com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.skynet.infrastructure.CacheService;
import com.skynet.infrastructure.ESClient;
import com.skynet.infrastructure.GraphService;
import com.skynet.infrastructure.EventService;
import com.skynet.infrastructure.SMTPService;
import com.skynet.infrastructure.BlockChainAdvancer;
import com.terapico.caf.BeanFactory;
import com.terapico.caf.BlobObject;
import com.terapico.caf.InvocationResult;
import com.skynet.infrastructure.MessageService;
import com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>.tree.*;

// Default implementation
public class UserContextImpl implements UserContext {
    protected CacheService cacheService;
    protected BlockChainAdvancer blockChainAdvancer;
    protected ESClient esClient;
    protected SMTPService smtpService;
    protected TreeServiceImpl treeService;
    protected String remoteIP;
    protected String tokenId;
    protected String userAgent;
    protected BeanFactory beanFactory;
    protected String publicMediaServicePrefix;
    protected MessageService messageService;protected<%=checkManagerClazz%>customCheckManager;
    protected Map<String, Object> requestParameters;
    protected Map<String, String> requestHeaders;
    protected Map<String, String> responseHeaders;
    protected Map<String, Object> contextLocalStorage;
    protected String assignedRenderingWay;
    protected byte[] requestBody;
    protected String environmentName;
    protected Boolean productEnvironment;
    protected DAOGroup daoGroup;
    protected ManagerGroup managerGroup;
    protected Cookie[] requestCookies;
    protected LocationService locationService;

    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    public Cookie[] getRequestCookies() {
        return requestCookies;
    }

    public TreeServiceImpl getTreeService() {
        return treeService;
    }

    public void setTreeService(TreeServiceImpl treeService) {
        this.treeService = treeService;
    }

    public void setRequestCookies(Cookie[] requestCookies) {
        this.requestCookies = requestCookies;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public DAOGroup getDAOGroup() {
        return this.daoGroup;
    }

    public void setDaoGroup(DAOGroup daoGroup) {
        this.daoGroup = daoGroup;
    }

    public ManagerGroup getManagerGroup() {
        return this.managerGroup;
    }

    public void setManagerGroup(ManagerGroup managerGroup) {
        this.managerGroup = managerGroup;
    }

    public String getEnvironmentName() {
        if (environmentName == null || environmentName.isEmpty()) {
            String name = System.getenv("SKY_ENVIRONMENT_NAME");
            if (name == null || name.isEmpty()) {
                return "dev_default";
            }
            return name;
        }
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public boolean isProductEnvironment() {
        if (productEnvironment == null) {
            String name = System.getenv("SKY_ENVIRONMENT_NAME");
            if ("product".equals(name)) {
                return true;
            }
            if (name == null) {
                return false;
            }
            if (name.startsWith("product_")) {
                return true;
            }
            return false;
        }
        return productEnvironment.booleanValue();
    }

    public void setProductEnvironment(Boolean productEnvironment) {
        this.productEnvironment = productEnvironment;
    }

    @Override
    public void assignRenderingWay(String renderingWay) {
        assignedRenderingWay = renderingWay;
    }

    @Override
    public String getAssignedRederingWay() {
        return assignedRenderingWay;
    }

    public byte[] getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(byte[] requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, Object> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, Object> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Map<String, Object> getContextLocalStorage() {
        return contextLocalStorage;
    }

    public void setContextLocalStorage(Map<String, Object> contextLocalStorage) {
        this.contextLocalStorage = contextLocalStorage;
    }

    public ESClient getEsClient() {
        return esClient;
    }

    public void setEsClient(ESClient esClient) {
        this.esClient = esClient;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getPublicMediaServicePrefix() {
        return publicMediaServicePrefix;
    }

    public void setPublicMediaServicePrefix(String publicMediaServicePrefix) {
        this.publicMediaServicePrefix = publicMediaServicePrefix;
    }

    public <%=checkManagerClazz%> getCustomCheckManager(){
		return customCheckManager;
	}

    public void setCustomCheckManager(<%=checkManagerClazz%> customCheckManager){
		this.customCheckManager = customCheckManager;
	}

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setSmtpService(SMTPService smtpService) {
        this.smtpService = smtpService;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setCacheService(CacheService cacheService) {

        this.cacheService = cacheService;
    }

    public void putToCache(String key, Object value, int timeToLiveInSeconds) {
        ensureCacheService();
        cacheService.put(key, value, timeToLiveInSeconds);
    }

    public BlockChainAdvancer getBlockChainAdvancer() {
        return blockChainAdvancer;
    }

    public void setBlockChainAdvancer(BlockChainAdvancer pBlockChainAdvancer) {
        blockChainAdvancer = pBlockChainAdvancer;
    }

    public void sendEmail(String to, String subject, String content) throws Exception {
        this.ensureSMTPService();
        smtpService.send(to, subject, content);

    }

    public void sendEmailWithAttachment(String to, String subject, String content, List<BlobObject> attachments)
            throws Exception {
        this.ensureSMTPService();
        smtpService.sendWithAttachment(to, subject, content, attachments);
    }

    public <T> T getCachedObject(String key, Class<T> clazz) {
        ensureCacheService();
        return (T) cacheService.get(key, clazz);
    }

    protected void ensureCacheService() {
        if (cacheService == null) {
            throw new IllegalStateException("cacheService is not configured for a instance of UserContextImpl");
        }
    }

    protected void ensureSMTPService() {
        if (smtpService == null) {
            throw new IllegalStateException("smtpService is not configured for a instance of UserContextImpl");
        }
    }

    public void setRemoteIP(String remoteAddr) {

        this.remoteIP = remoteAddr;
    }

    public void setTokenId(String id) {

        this.tokenId = id;
    }

    public String tokenId() {

        return this.tokenId;
    }

    public void setUserAgent(String userAgent) {

        this.userAgent = userAgent;
    }

    protected String timeExpr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd'T'HH:mm:ss.SSS");
        // It is not thread safe, how silly the JDK is!!!
        return simpleDateFormat.format(new java.util.Date());
    }

    protected StringBuilder logBuffer;

    protected void addLog(String message) {
        if (logBuffer == null) {
            logBuffer = new StringBuilder();
        }
        logBuffer.append(message);
        logBuffer.append("\n");

    }

    public String reportExecution() {

        if (logBuffer == null) {
            return "NO LOG";
        }
        return logBuffer.toString();
    }

    public void log(String message) {
        String logMessage = timeExpr() + ": " + this.tokenId + "@" + this.remoteIP + ": " + message;
        addLog(logMessage);
        System.out.println(logMessage);
    }

    protected Date getCurrentTime() {
        return new Date();// provide a way to shift the time for some use cases;
    }

    public void cacheUser(Object value) {

        this.putToCache(this.tokenId, value, 1000);
    }

    public Object userOf(Class<?> clazz) {

        return this.getCachedObject(this.tokenId, clazz);
    }

    public void removeFromCache(String key) {

        ensureCacheService();
        cacheService.remove(key);
    }

    public Object getBean(String beanName) {
        if (getBeanFactory() == null) {
            this.log("getBeanFactory() is not initialized");
            return null;
        }

        return getBeanFactory().getBean(beanName);
    }

    public UserContext castTo(Class<UserContext> targetClass) throws InstantiationException, IllegalAccessException {
        UserContextImpl newUserContext = (UserContextImpl) targetClass.newInstance();

        newUserContext.setBeanFactory(this.getBeanFactory());

        newUserContext.setCacheService(this.cacheService);
        newUserContext.setRemoteIP(this.getRemoteIP());
        newUserContext.setSmtpService(this.smtpService);
        newUserContext.setTokenId(this.tokenId());
        newUserContext.setUserAgent(this.userAgent);
        return newUserContext;
    }

    public void setGraphService(GraphService graphService) {
        this.graphService = graphService;
    }

    public GraphService getGraphService() {
        return graphService;
    }

    private GraphService graphService;
    private EventService eventService;

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public List<String[]> relationBetween(String sourceType, String sourceId, String targetType, String targetId) {
<%if(noneOf(name,new String[]{"bbt"})){%>
        if(graphService==null){
            throw new IllegalStateException("graphService must be configured");
        }
        return graphService.relationsOf(BaseManagerImpl.getSystemInternalName(),sourceType,sourceId,targetType,targetId);
<%}else{%>
        return null;
<%}%>
    }

    private Map<String, Object> accessTokens;

    public void addAccessTokens(Map<String, Object> tokens) {
        // TODO Auto-generated method stub
        ensureAccessTokens();
        accessTokens.putAll(tokens);
    }

    protected void ensureAccessTokens() {
        if (accessTokens == null) {
            accessTokens = new HashMap<String, Object>();
        }
    }

    public Map<String, Object> getAccessTokens() {
        ensureAccessTokens();
        return accessTokens;
    }

    public DateTime now() {
        // TODO Auto-generated method stub
        return DateTime.fromDate(new Date());
    }

    public String currentUserName() {
        // TODO Auto-generated method stub
        return "PhilipGreat";
    }

    @Override
    public void sendMessage(String dest, String fromWho, String template, Map<String, String> parameters)
            throws Exception {
        if (getMessageService() == null) {
            throw new IllegalStateException("The message service is not configured before is can be used");
        }

        this.getMessageService().sendMessage(dest, fromWho, template, parameters);

    }

    @Override
    public void forceRenderingAsJson() {
        assignRenderingWay("json");
    }

    @Override
    public void forceRenderingAsHtml() {
        assignRenderingWay("html");
    }

    @Override
    public void forceRenderingAsJavaScript() {
        assignRenderingWay("javascript");
    }

    private String prefferedAppType;

    public void setPrefferedAppType(String prefferedAppType) {
        this.prefferedAppType = prefferedAppType;
    }

    @Override
    public Object getFromContextLocalStorage(String key) {
        ensureContextLocalStorage();
        return contextLocalStorage.get(key);
    }

    private void ensureContextLocalStorage() {
        if (contextLocalStorage == null) {
            contextLocalStorage = new HashMap<String, Object>();
        }
    }

    @Override
    public void putIntoContextLocalStorage(String key, Object value) {
        ensureContextLocalStorage();
        contextLocalStorage.put(key, value);
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public void putHeader(String name, String value) {
        ensureHeaders();
        requestHeaders.put(name, value);
    }

    @Override
    public String getRequestHeader(String name) {
        ensureHeaders();
        return requestHeaders.get(name);
    }

    protected void ensureHeaders() {
        if (requestHeaders != null) {
            return;
        }
        requestHeaders = new HashMap<>();
    }

    protected void ensureResHeaders() {
        if (responseHeaders != null) {
            return;
        }
        responseHeaders = new HashMap<>();
    }

    @Override
    public void setResponseHeader(String name, String value) {
        ensureResHeaders();
        responseHeaders.put(name, value);
    }

    @Override
    public String getResponseHeadder(String name) {
        ensureResHeaders();
        return responseHeaders.get(name);
    }

    @Override
    public void forceResponseXClassHeader(String clazzName) {
        setResponseHeader("X-Class", clazzName);
    }

    public void setChecker(<%=oc.getNameCapitalizeFirst() %>ObjectChecker checker) {
		//Let <%=oc.getNameCapitalizeFirst() %> do the job :)

	}

    @Override
    public <T> List<T> getCachedObjectsWithOneType(List<String> keys, Class<T> clazz) {
        ensureCacheService();
        return (List<T>) cacheService.mget(keys, clazz);
    }

}

@savefile:<%=coreSrcDestPath%>/com/<%=oc.getUniStr().getNameSpace()%>/<%=oc.getNameCapitalizeFirst().toLowerCase()%>/<%=oc.getNameCapitalizeFirst()%>UserContext.java 
package com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>;

    public interface<%=oc.getNameCapitalizeFirst()%>UserContext extends UserContext{

    // define the domain specific user model
    String getLocaleKey(String subject);

    void setChecker(<%=oc.getNameCapitalizeFirst() %>ObjectChecker checker);
	<%=oc.getNameCapitalizeFirst() %>

    ObjectChecker getChecker();

    void saveAccessInfo(String beanName, String methodName, Object[] parameters);

    void addFootprint(FootprintProducer helper) throws Exception;

    Object getPreviousViewPage() throws Exception;

    Object getLastViewPage() throws Exception;

    Object goback() throws Exception;
}

@savefile:<%=coreSrcDestPath%>/com/<%=oc.getUniStr().getNameSpace()%>/<%=oc.getNameCapitalizeFirst().toLowerCase()%>/<%=oc.getNameCapitalizeFirst()%>UserContextImpl.java 
package com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>.secuser.SecUser;
import com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>.secuser.SecUserCustomManagerImpl;
import com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>.userapp.UserApp;
import com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>.userapp.UserAppTokens;
import com.terapico.utils.CollectionUtils;

public class<%=oc.getNameCapitalizeFirst()%>UserContextImpl extends UserContextImpl implements<%=oc.getNameCapitalizeFirst()%>UserContext{
    // implements the domain specific user model

    // 默认支持中文和英文
    protected static Map<String, String> chineseMap;

    protected static Map<String, String> englishMap;

    protected double longitude;
    protected double latitude;

    static final String RESOURCE_PATH = "com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase() %>.<%=oc.getNameCapitalizeFirst() %>Resources";
    static final String CUSTOM_RESOURCE_PATH = "com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase() %>.<%=oc.getNameCapitalizeFirst() %>CustomResources";

    public Map<String, String> ensureLocaleMaps(Locale locale) {

        String[] resources = { RESOURCE_PATH, CUSTOM_RESOURCE_PATH };
        return ensureResourceAddResourceMaps(resources, locale);

    }

    protected Map<String, String> ensureResourceAddResourceMaps(String[] paths, Locale locale) {
        Map<String, String> localeMap = new HashMap<String, String>();
        for (String path : paths) {
            try {
                ResourceBundle resourceBundle = ResourceBundle.getBundle(path, locale);
                addResourceToMap(localeMap, resourceBundle);
            } catch (MissingResourceException e) {
                // the resource can be missed and it is fine
            }

        }
        return localeMap;
    }

    protected void addResourceToMap(Map<String, String> localeMap, ResourceBundle resourceBundle) {
        Enumeration<String> bundleKeys = resourceBundle.getKeys();

        while (bundleKeys.hasMoreElements()) {
            String key = (String) bundleKeys.nextElement();
            String value = resourceBundle.getString(key);
            // System.out.println("key = " + key + ", " + "value = " + value);
            localeMap.put(key, value);
        }

    }

    public void init() {
        if (chineseMap == null) {
            chineseMap = ensureLocaleMaps(Locale.SIMPLIFIED_CHINESE);
        }
        if (englishMap == null) {
            englishMap = ensureLocaleMaps(Locale.US);
        }

    }

    public Map<String, String> getLocaleMap() {
        init();
    <%if(oc.defaultLanguage().equalsIgnoreCase("Chinese")){%>
        return chineseMap;
    <%}else{%>
        return englishMap;
    <%}%>
    }

    protected Locale getLocale() {
        return Locale.US;
    }

    public String getLocaleKey(String subject) {
        return getLocaleMap().get(subject);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /*
     * public static SecUser getSecUser(<%=oc.getNameCapitalizeFirst() %>UserContext
     * userContext) { SecUserCustomManagerImpl secUserManager =
     * (SecUserCustomManagerImpl) userContext.getManagerGroup()
     * .getSecUserManager(); SecUser secUser =
     * secUserManager.getCachedSecUser(userContext); return secUser; }
     * 
     * public static UserApp
     * getSingleUserAppByObjectName(<%=oc.getNameCapitalizeFirst() %>UserContext
     * userContext, String objectName) throws Exception { SecUserCustomManagerImpl
     * secUserManager = (SecUserCustomManagerImpl) userContext.getManagerGroup()
     * .getSecUserManager(); SecUser secUser =
     * secUserManager.getCachedSecUser(userContext); if (secUser == null) { return
     * null; } SmartList<UserApp> appList = getUserAppByName(userContext, secUser,
     * objectName); if (appList == null) { return null; } if (appList.size() == 1) {
     * return appList.get(0); } String appId =
     * secUserManager.getCurrentAppKey(userContext); for (UserApp app : appList) {
     * if (app.getId().equals(appId)) { return app; } } return null; }
     */
    public static SmartList<UserApp> getUserAppByName(<%=oc.getNameCapitalizeFirst()%>UserContext userContext,SecUser secUser,String objectName) {
        MultipleAccessKey key=new MultipleAccessKey();
        key.put(UserApp.SEC_USER_PROPERTY,secUser.getId());
        key.put(UserApp.OBJECT_TYPE_PROPERTY,objectName);
        SmartList<UserApp>appList=userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key,UserAppTokens.all());
        filterInvalidApps(appList);
        if(CollectionUtils.isEmpty(appList)){
            return null;
        }
        return appList;
    }

    private static void filterInvalidApps(SmartList<UserApp> appList) {
        if (CollectionUtils.isEmpty(appList)) {
            return;
        }
        Iterator<UserApp> it = appList.iterator();
        while (it.hasNext()) {
            UserApp app = it.next();
            if (app.getSecUser() == null) {
                it.remove();
                continue;
            }
        }
    }

    public static UserApp getUserAppByBindedEntity(<%=oc.getNameCapitalizeFirst() %>UserContext userContext, BaseEntity userAppBindedObject) {
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserApp.OBJECT_TYPE_PROPERTY, userAppBindedObject.getInternalType());
		key.put(UserApp.OBJECT_ID_PROPERTY, userAppBindedObject.getId());
		SmartList<UserApp> apps = userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key,
				UserAppTokens.all());
		if (apps == null) {
			return null;
		}
		Iterator<UserApp> it = apps.iterator();
		while (it.hasNext()) {
			// 过滤掉无效的app
			UserApp app = it.next();
			if (app.getSecUser() == null) {
				it.remove();
				continue;
			}
		}
		if (apps.isEmpty()) {
			return null;
		}
		return apps.get(0);
	}

	private <%=oc.getNameCapitalizeFirst() %>ObjectChecker checker;

    public void setChecker(<%=oc.getNameCapitalizeFirst() %>ObjectChecker checker) {
		this.checker = checker;

	}

	@Override
	public <%=oc.getNameCapitalizeFirst() %>

    ObjectChecker getChecker() {

        if (this.checker == null) {
            throw new IllegalStateException("每个实例必须配置Checker，请检查相关Spring的XML配置文件中 checker的配置");
        }
        checker.setUserContext(this);
        return checker;
    }

    protected static final String ACCESS_PARAMETERS_KEY = "$access_parameters";
    protected static final String ACCESS_METHOD_NAME_KEY = "$access_method_name";
    protected static final String ACCESS_BEAN_NAME_KEY = "$access_bean_name";

    public void saveAccessInfo(String beanName, String methodName, Object[] parameters) {
        putIntoContextLocalStorage(ACCESS_BEAN_NAME_KEY, beanName);
        putIntoContextLocalStorage(ACCESS_METHOD_NAME_KEY, methodName);
        putIntoContextLocalStorage(ACCESS_PARAMETERS_KEY, parameters);
        putToCache(getFootprintMarkKey(), true, 1 * 60 * 60);
    }

    protected String getFootprintMarkKey() {
        return tokenId() + ":$acces_page_without_foorprint";
    }

    public void addFootprint(FootprintProducer helper) throws Exception {
        String beanName = (String) this.getFromContextLocalStorage(ACCESS_BEAN_NAME_KEY);
        String methodName = (String) this.getFromContextLocalStorage(ACCESS_METHOD_NAME_KEY);
        Object[] parameters = (Object[]) this.getFromContextLocalStorage(ACCESS_PARAMETERS_KEY);
        if (beanName == null) {
            throw new Exception(
                    "Please make sure you had invoke user-context saveAccessInfo() inside your onAccess().");
        }
        if (!beanName.equals(helper.getBeanName())) {
            throw new Exception("This request was from " + beanName + ", not from " + helper.getBeanName() + ".");
        }
        if (methodName == null) {
            return;// 没保存过，就不用处理了。
        }
        this.removeFromCache(getFootprintMarkKey());
        // 核心问题是怎么处理堆栈。 是持续累加，还是‘短路’算法（clear_top)，或者‘提升’（brought—to-front）
        Footprint fp = new Footprint();
        fp.setBeanName(helper.getBeanName());
        fp.setMethodName(methodName);
        fp.setParameters(parameters);
        // 先从缓存中拿到历史记录
        List<Footprint> history = getFootprintListFromCache();
        if (history == null || history.isEmpty()) {
            // 历史是空的，直接追加
            history = new ArrayList<>();
            history.add(fp);
            this.log("add footprint " + beanName + "." + methodName + "(" + Arrays.asList(fp.getParameters()) + ")");
            putFootprintIntoCache(history);
            return;
        }

        Footprint replacedFp = null;
        for (Footprint item : history) {
            if (helper.canReplaceFootPrint(fp, item)) {
                replacedFp = item;
                break;
            }
        }

        if (replacedFp == null) {
            // 没找到可替换的目标，追加到队列最后
            history.add(fp);
            this.log(
                    "add new footprint " + beanName + "." + methodName + "(" + Arrays.asList(fp.getParameters()) + ")");
            putFootprintIntoCache(history);
            return;
        }

        // 找到可替换的目标以后，还要决定怎么做
        if (helper.clearTop()) {
            Iterator<Footprint> it = history.iterator();
            boolean found = false;
            while (it.hasNext()) {
                Footprint item = it.next();
                if (item == replacedFp) {
                    found = true;
                }
                if (found) {
                    it.remove();
                }
            }
        } else {
            int idx = history.indexOf(replacedFp);
            history.remove(idx);
        }
        history.add(fp);
        this.log("replace footprint " + beanName + "." + methodName + "(" + Arrays.asList(fp.getParameters()) + ")");
        putFootprintIntoCache(history);
    }

    public int getFootprintDeepth() throws Exception {
        List<Footprint> history = getFootprintListFromCache();
        if (history == null || history.isEmpty()) {
            return 0;
        }
        return history.size();
    }

    protected void putFootprintIntoCache(List<Footprint> history) throws Exception {
        String historyJson = new ObjectMapper().writeValueAsString(history);
        this.putToCache(getFootprintKey(), historyJson, 1 * 60 * 60); // 1个小时
    }

    protected List<Footprint> getFootprintListFromCache() throws Exception {
        String historyJson = (String) this.getCachedObject(getFootprintKey(), String.class);
        if (historyJson == null || historyJson.isEmpty()) {
            return null;
        }
        return new ObjectMapper().readValue(historyJson, new TypeReference<List<Footprint>>() {
        });
    }

    protected String getFootprintKey() {
        return this.tokenId + ":footprint";
    }

    public Object getLastViewPage() throws Exception {
        List<Footprint> history = getFootprintListFromCache();
        if (history == null || history.isEmpty()) {
            return null;
        }

        Footprint fp = history.remove(history.size() - 1);
        putFootprintIntoCache(history);

        Object service = this.getBean(fp.getBeanName());
        fp.getParameters()[0] = this;
        Method[] methods = service.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals(fp.getMethodName()) && m.getParameterTypes().length == fp.getParameters().length) {
                return m.invoke(service, fp.getParameters());
            }
        }
        return null;
    }

    public Object getPreviousViewPage() throws Exception {
        List<Footprint> history = getFootprintListFromCache();
        if (history == null || history.isEmpty()) {
            return null;
        }

        Footprint fp = history.remove(history.size() - 1);
        if (history.isEmpty()) {
            putFootprintIntoCache(history);
            return null;
        }
        fp = history.remove(history.size() - 1);
        putFootprintIntoCache(history);

        Object service = this.getBean(fp.getBeanName());
        fp.getParameters()[0] = this;
        Method[] methods = service.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals(fp.getMethodName()) && m.getParameterTypes().length == fp.getParameters().length) {
                return m.invoke(service, fp.getParameters());
            }
        }
        return null;
    }

    public Object goback() throws Exception {
        Object mark = getCachedObject(getFootprintMarkKey(), Boolean.class);
        if (mark instanceof Boolean && ((Boolean) mark).booleanValue()) {
            // 没压栈
            return getLastViewPage();
        }
        return getPreviousViewPage();
    }

}

@savefile:<%=customSrcDestPath%>/com/<%=oc.getUniStr().getNameSpace()%>/<%=oc.getNameCapitalizeFirst().toLowerCase()%>/<%=oc.getNameCapitalizeFirst()%>BizUserContextImpl.java 
package com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.terapico.caf.BlobObject;
import com.terapico.caf.appview.ChangeRequestData;
import com.terapico.caf.appview.ChangeRequestProcessResult;
import com.terapico.caf.baseelement.LoginParam;
import com.terapico.caf.viewcomponent.ButtonViewComponent;
import com.terapico.caf.viewcomponent.FilterTabsViewComponent;
import com.terapico.caf.viewcomponent.PopupViewComponent;
<%if(oc.isConfigAs("payment_service","basic")){%>
import com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>.paymentsucceedrecord.PaymentSucceedRecord;
<%}else{%>
<%}%>
import com.terapico.utils.TextUtil;
import com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>.secuser.SecUser;

public class<%=oc.getNameCapitalizeFirst()%>BizUserContextImpl extends<%=oc.getNameCapitalizeFirst()%>UserContextImpl{

    protected static final String DEFAULT_ACTION_GROUP = "default";
    protected Map<String, List<ButtonViewComponent>> actionGroups;
    protected String accessUrl;
    protected Object resultObject;
    protected String mobile;
    protected String userId;
    protected String lastRecordId;
    protected String verifyCode;
    protected String filter;
    protected String orderId;
    protected String formKey;
    protected String wechatEncryptedData;
    protected String wechatOpenId;
    protected String wechatIv;
    protected String message;
    protected PopupViewComponent popup;
    protected Map<String, Object> toast;
    protected String accessToken;
    protected String jwtKeyId;
    protected FilterTabsViewComponent tabs;
    protected SecUser secUser;
    protected String inputJwtToken;
    protected String assignmentId;protected Base<%=oc.getNameCapitalizeFirst()%>
    FormProcessor inputFormData;protected Base<%=oc.getNameCapitalizeFirst()%>
    FormProcessor outputFormData;
    protected LoginParam loginParam;
    protected BaseEntity currentUserInfo;
    protected ChangeRequestData changeRequestResponse;
    protected ChangeRequestProcessResult changeRequestProcessResult;

<%if(oc.isConfigAs("payment_service","basic")){%>
    protected PaymentSucceedRecord paymentSucceedRecord;

    public PaymentSucceedRecord getPaymentSucceedRecord() {
        return paymentSucceedRecord;
    }

    public void setPaymentSucceedRecord(PaymentSucceedRecord paymentSucceedRecord) {
        this.paymentSucceedRecord = paymentSucceedRecord;
    }
<%}%>

    public void clearFormResubmitFlag() {
        removeFromCache(getPostMd5Key(this));
    }

    public String getPostMd5Key(<%=oc.getNameCapitalizeFirst()%>BizUserContextImpl ctx) {
        return ctx.tokenId()+":postMd5Key";
    }

    public void checkOnlyCalledByLocalhost() throws Exception {
        String ip = getRemoteIP();
        if (!ip.equals("127.0.0.1") && isProductEnvironment()) {
            throw new Exception("本接口只能在本地调用");
        }
    }

    public BaseEntity getCurrentUserInfo() {
        return currentUserInfo;
    }

    public void setCurrentUserInfo(BaseEntity currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    public void setEventResult(String eventName, int processResult) {
        this.putIntoContextLocalStorage("event_" + eventName + "_result", processResult);
    }

    public Integer getEventResult(String eventName) {
        return (Integer) this.getFromContextLocalStorage("event_" + eventName + "_result");
    }

    public void addAction(String title, String code, String linkToUrl) {
        addAction(DEFAULT_ACTION_GROUP, title, code, linkToUrl);
    }

    public void addAction(ButtonViewComponent actionButton) {
        addAction(DEFAULT_ACTION_GROUP, actionButton);
    }

    public List<ButtonViewComponent> getActionList() {
        return getActionList(DEFAULT_ACTION_GROUP);
    }

    public List<ButtonViewComponent> getActionList(String groupName) {
        return ensureActionGroups(groupName);
    }

    public Map<String, ButtonViewComponent> getActions() {
        return getActions(DEFAULT_ACTION_GROUP);
    }

    public Map<String, ButtonViewComponent> getActions(String groupName) {
        List<ButtonViewComponent> actions = ensureActionGroups(groupName);
        Map<String, ButtonViewComponent> resultMap = new HashMap<>();
        for (ButtonViewComponent action : actions) {
            resultMap.put(action.getTag(), action);
        }
        return resultMap;
    }

    public ButtonViewComponent addAction(String groupName, String title, String code, String linkToUrl) {
        List<ButtonViewComponent> actionList = ensureActionGroups(groupName);
        ButtonViewComponent btn = new ButtonViewComponent(title);
        btn.setTag(code);
        btn.setLinkToUrl(linkToUrl);
        actionList.add(btn);
        return btn;
    }

    public ButtonViewComponent addAction(String groupName, ButtonViewComponent actionButton) {
        ensureActionGroups(groupName).add(actionButton);
        return actionButton;
    }

    private List<ButtonViewComponent> ensureActionGroups(String groupName) {
        if (actionGroups == null) {
            actionGroups = new HashMap<>();
        }
        if (TextUtil.isBlank(groupName)) {
            return null;
        }
        List<ButtonViewComponent> actionList = actionGroups.get(groupName);
        if (actionList == null) {
            actionList = new ArrayList<>();
            actionGroups.put(groupName, actionList);
        }
        return actionList;
    }

    public Map<String, List<ButtonViewComponent>> getActionGroups() {
        return actionGroups;
    }

    public void setActionGroups(Map<String, List<ButtonViewComponent>> actionGroups) {
        this.actionGroups = actionGroups;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastRecordId() {
        return lastRecordId;
    }

    public void setLastRecordId(String lastRecordId) {
        this.lastRecordId = lastRecordId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getFilter() {
        return filter;
    }

    public String getFilter(String defaulValue) {
        if (TextUtil.isBlank(filter)) {
            setFilter(defaulValue);
            return defaulValue;
        }
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getWechatEncryptedData() {
        return wechatEncryptedData;
    }

    public void setWechatEncryptedData(String wechatEncryptedData) {
        this.wechatEncryptedData = wechatEncryptedData;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public String getWechatIv() {
        return wechatIv;
    }

    public void setWechatIv(String wechatIv) {
        this.wechatIv = wechatIv;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PopupViewComponent getPopup() {
        return popup;
    }

    public void setPopup(PopupViewComponent popup) {
        this.popup = popup;
    }

    public Map<String, Object> getToast() {
        return toast;
    }

    public void setToast(Map<String, Object> toast) {
        this.toast = toast;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Base<%=oc.getNameCapitalizeFirst()%>FormProcessor getInputFormData() {
        return inputFormData;
    }

    public void setInputFormData(Base<%=oc.getNameCapitalizeFirst()%>FormProcessor inputFormData) {
		this.inputFormData = inputFormData;
	}
	public Base<%=oc.getNameCapitalizeFirst()%>

    FormProcessor getOutputFormData() {
        return outputFormData;
    }

    public void setOutputFormData(Base<%=oc.getNameCapitalizeFirst()%>FormProcessor outputFormData) {
        this.outputFormData=outputFormData;
    }

    public String getJwtKeyId() {
        return jwtKeyId;
    }

    public void setJwtKeyId(String jwtKeyId) {
        this.jwtKeyId = jwtKeyId;
    }

    public FilterTabsViewComponent getTabs() {
        return tabs;
    }

    public void setTabs(FilterTabsViewComponent tabs) {
        this.tabs = tabs;
    }

    public SecUser getSecUser() {
        return secUser;
    }

    public void setSecUser(SecUser secUser) {
        this.secUser = secUser;
    }

    public String getInputJwtToken() {
        return inputJwtToken;
    }

    public void setInputJwtToken(String inputJwtToken) {
        this.inputJwtToken = inputJwtToken;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public LoginParam getLoginParam() {
        return loginParam;
    }

    public void setLoginParam(LoginParam loginParam) {
        this.loginParam = loginParam;
    }

    public ChangeRequestData getChangeRequestResponse() {
        return changeRequestResponse;
    }

    public void setChangeRequestResponse(ChangeRequestData changeRequestResponse) {
        this.changeRequestResponse = changeRequestResponse;
    }

    public ChangeRequestProcessResult getChangeRequestProcessResult() {
        return changeRequestProcessResult;
    }

    public void setChangeRequestProcessResult(ChangeRequestProcessResult changeRequestProcessResult) {
        this.changeRequestProcessResult = changeRequestProcessResult;
    }

    @Override
    public void sendEmail(String to, String subject, String content) throws Exception {
        if (!isProductEnvironment()) {
            to = "wangyudong@doublechaintech.com";
        }
        super.sendEmail(to, subject, content);
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String content, List<BlobObject> attachments)
            throws Exception {
        if (!isProductEnvironment()) {
            to = "wangyudong@doublechaintech.com";
        }
        super.sendEmailWithAttachment(to, subject, content, attachments);
    }

    @Override
    public void sendMessage(String dest, String fromWho, String template, Map<String, String> parameters)
            throws Exception {
        if (!isProductEnvironment()) {
            System.out.printf("send to %s: %s:%s\n", dest, template, String.valueOf(parameters));
            return; // 短信直接不发
        }
        super.sendMessage(dest, fromWho, template, parameters);
    }

    // 这个对象仅用于在开发环境中, 用cookie来模拟react-client的 JWT header. 因为开发环境使用的是普通的浏览器,不能在A
    // href的header中设置值.
    protected Cookie[] cookies;

    public Cookie[] getCookies() {
        return cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
}

@savefileifnotexist:<%=customSrcDestPath%>/com/<%=oc.getUniStr().getNameSpace()%>/<%=oc.getNameCapitalizeFirst().toLowerCase()%>/Custom<%=oc.getNameCapitalizeFirst()%>UserContextImpl.java 
package com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>;

public class Custom<%=oc.getNameCapitalizeFirst()%>UserContextImpl extends<%=oc.getNameCapitalizeFirst()%>BizUserContextImpl {
    protected BaseEntity currentUserInfo;

    public BaseEntity getCurrentUserInfo() {
        return currentUserInfo;
    }

    public void setCurrentUserInfo(BaseEntity currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

}

@savefileifnotexist:<%=customSrcDestPath%>/com/<%=oc.getUniStr().getNameSpace()%>/<%=oc.getNameCapitalizeFirst().toLowerCase()%>/FootprintProducer.java 
package com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>;

public interface FootprintProducer {

    boolean canReplaceFootPrint(Footprint fp, Footprint item);

    boolean clearTop();

    String getBeanName();
}

@savefileifnotexist:<%=customSrcDestPath%>/com/<%=oc.getUniStr().getNameSpace()%>/<%=oc.getNameCapitalizeFirst().toLowerCase()%>/Footprint.java 
package com.<%=oc.getUniStr().getNameSpace()%>.<%=oc.getNameCapitalizeFirst().toLowerCase()%>;

public class Footprint {
    protected String beanName;
    protected String methodName;
    protected Object[] parameters;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        if (parameters == null || parameters.length == 0) {
            this.parameters = new Object[] { null };
            return;
        }
        this.parameters = new Object[parameters.length];
        System.arraycopy(parameters, 0, this.parameters, 0, parameters.length);
        this.parameters[0] = null;
    }

}
