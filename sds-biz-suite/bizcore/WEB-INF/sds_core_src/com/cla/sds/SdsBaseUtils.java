package com.cla.sds;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.terapico.caf.form.ImageInfo;
import com.terapico.caf.viewcomponent.ButtonViewComponent;
import com.terapico.utils.TextUtil;

import com.cla.sds.changerequesttype.ChangeRequestType;
import com.cla.sds.pagetype.PageType;

public class SdsBaseUtils {
	protected static final Map<String, Object> emptyOptions = new HashMap<>();
	protected static final Map<String, Object> EO = new HashMap<>();

	public static String getOssUploadFolderName(String tokenType, String token, boolean isProdEnv) {
		String folderName;
		folderName = String.format("upload%s/%s/%s", isProdEnv ? "" : "/test", tokenType, token);
		return folderName;
	}
	
	public static String hashWithSHA256(String valueToHash, String salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			String textToHash = valueToHash+":"+salt;
			byte[] hash = digest.digest(textToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder stringBuilder = new StringBuilder();
		    for (byte b : hash) {
		        stringBuilder.append(String.format("%02X", b));
		    }
		    return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static String formatChinaMobile(String mobile) {
		return TextUtil.formatChinaMobile(mobile);
	}
	
	public static String checkChinaMobile(String mobile) throws Exception {
		String cleanMobile = formatChinaMobile(mobile);
		if (cleanMobile == null) {
			throw new Exception("您输入的"+mobile+"不是有效的中国大陆手机号");
		}
		return cleanMobile;
	}
	
	public static String getCacheAccessKey(SdsUserContext ctx) {
		return ctx.tokenId()+":access_page_without_footprint";
	}
	public static <T> Set<Object> toSet(List<T> list, Function<T, ? extends Object> mapper) {
		return list.stream().map(mapper).collect(Collectors.toSet());
	}
	
	protected static BaseEntity loadCanCacheInLocal(SdsUserContext userContext, String type, String id) throws Exception {
		String key = "baseentity:"+type+":"+id;
		BaseEntity result = (BaseEntity) userContext.getFromContextLocalStorage(key);
		if (result != null) {
			return result;
		}
		result = userContext.getDAOGroup().loadBasicData(type, id);
		userContext.putIntoContextLocalStorage(key, result);
		return result;
	}
	
	public static <T extends BaseEntity> List<T> collectReferencedObjectWithType(SdsUserContext userContext,
			BaseEntity rootObject, Class<T> clazz) throws Exception {
		List<T> referedObject = new LinkedList<>();
		Set<Object> checkedObject = new HashSet<>();
		collectReferedObjectIdSet(userContext, referedObject, checkedObject, rootObject, clazz, "/");
		return referedObject;
	}

	public static <T extends BaseEntity> List<T> collectReferencedObjectWithType(SdsUserContext userContext,
			SmartList<? extends BaseEntity> rootObjectList, Class<T> clazz) throws Exception {
		return collectReferencedObjectWithType(userContext, (List<BaseEntity>) rootObjectList, clazz);
	}

	public static <T extends BaseEntity> List<T> collectReferencedObjectWithType(SdsUserContext userContext,
			List<? extends BaseEntity> rootObjectList, Class<T> clazz) throws Exception {
		List<T> referedObject = new LinkedList<>();
		Set<Object> checkedObject = new HashSet<>();
		if (rootObjectList == null || rootObjectList.isEmpty()) {
			return referedObject;
		}
		for (BaseEntity refObj : rootObjectList) {
			collectReferedObjectIdSet(userContext, referedObject, checkedObject, refObj, clazz, "/");
		}
		return referedObject;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T extends BaseEntity> void collectReferedObjectIdSet(SdsUserContext userContext,
			List<T> referedObject, Set<Object> checkedObject, BaseEntity rootObject, Class<T> clazz, String path)
			throws Exception {
		if (rootObject == null) {
			return;
		}
		if (checkedObject.contains(rootObject)) {
			return;
		}
		checkedObject.add(rootObject);
		String pathKey = rootObject.hashCode() + "/";
		if (path.contains("/" + pathKey)) {
			// System.out.println("skip, since already collected object " + rootObject);
			return;
		}
		path += pathKey;
		if (clazz.isAssignableFrom(rootObject.getClass())) {
			referedObject.add((T) rootObject);
		}
		Field[] fields = rootObject.getClass().getDeclaredFields();
		for (Field field : fields) {
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			if (isStatic) {
				continue;
			}
			field.setAccessible(true);
			Object value = field.get(rootObject);
			if (List.class.isAssignableFrom(field.getType())) {
				field.setAccessible(true);
				List fieldList = (List) value;
				if (fieldList != null && !fieldList.isEmpty()) {
					for (int i = 0; i < fieldList.size(); i++) {
						Object objData = fieldList.get(i);
						if (objData instanceof BaseEntity) {
							collectReferedObjectIdSet(userContext, referedObject, checkedObject, (BaseEntity) objData,
									clazz, path);
						}
					}
				}
				continue;
			}
			if (value instanceof BaseEntity) {
				collectReferedObjectIdSet(userContext, referedObject, checkedObject, (BaseEntity) value, clazz, path);
				continue;
			}
		}
	}
	
	public static String getImageFromArray(List<ImageInfo> imageArray, int idx) {
		if (imageArray == null || imageArray.size() <= idx) {
			return null;
		}
		return imageArray.get(idx).getImageUrl();
	}
	
	public static boolean isDivisible(BigDecimal divisor, BigDecimal dividend) throws Exception {
		if (dividend.signum() == 0) {
			throw new Exception("请不要输入0");
		}
		BigDecimal[] quotient = divisor.divideAndRemainder(dividend);
		if (quotient.length == 1) {
			return true;
		}
		if (quotient[1].signum() == 0) {
			return true;
		}
		return false;
	}
	
	static Pattern ptnVersionSegment = Pattern.compile("\\d+");
	public static int getBuildVersion(String appVersionStr) {
		if (appVersionStr == null || appVersionStr.isEmpty()) {
			return 0;
		}
		int pos = appVersionStr.lastIndexOf(".");
		if (pos < 0) {
			return Integer.parseInt(appVersionStr);
		}
		//return Integer.parseInt(appVersionStr.substring(pos+1));
		List<String> list = TextUtil.findAllMatched(appVersionStr, ptnVersionSegment);
		return Integer.parseInt(list.get(0));
	}
	public static int getAppBuildVersion(SdsUserContext ctx) {
		return getBuildVersion(getRequestAppVersion(ctx));
	}
	protected static boolean startFromVersion(SdsUserContext ctx, int version) {
		if (!ctx.isProductEnvironment()) {
			return true;
		}
		return getAppBuildVersion(ctx) >= version;
	}
	/*
	 * "x-app-device" : "EML-AL00",
  	 * "x-app-type" : "CommunityUser",
     * "X-Forwarded-For" : "123.121.90.15",
  	 * "X-Real-IP" : "123.121.90.15",
     * "X-Forwarded-Server" : "app.art0x.com",
     * "x-app-version" : "9"
	 */
	public static String getRequestAppVersion(SdsUserContext userContext) {
		return userContext.getRequestHeader("x-app-version");
	}
	public static String getRequestAppDevice(SdsUserContext userContext) {
		return userContext.getRequestHeader("x-app-device");
	}
	public static String getRequestAppType(SdsUserContext userContext) {
		return userContext.getRequestHeader("x-app-type");
	}
	private static final NumberFormat cashFormat = new DecimalFormat("#,##0.00");
	private static final NumberFormat exRateFormat = new DecimalFormat("#,##0.00#");
	public static String formatCash(BigDecimal amount) {
		return cashFormat.format(amount)+"元";
	}
	public static String formatExchangeRate(BigDecimal amount) {
		return exRateFormat.format(amount);
	}


	public static ChangeRequestType getChangeRequestType(SdsUserContext userContext, String code) throws Exception {
		String key = "enum:" + ChangeRequestType.INTERNAL_TYPE + ":" + code;
		ChangeRequestType data = (ChangeRequestType) userContext.getFromContextLocalStorage(key);
		if (data != null) {
			return data;
		}
		data = userContext.getDAOGroup().getChangeRequestTypeDAO().loadByCode(code, emptyOptions);
		userContext.putIntoContextLocalStorage(key, data);
		return data;
	}


	public static PageType getPageType(SdsUserContext userContext, String code) throws Exception {
		String key = "enum:" + PageType.INTERNAL_TYPE + ":" + code;
		PageType data = (PageType) userContext.getFromContextLocalStorage(key);
		if (data != null) {
			return data;
		}
		data = userContext.getDAOGroup().getPageTypeDAO().loadByCode(code, emptyOptions);
		userContext.putIntoContextLocalStorage(key, data);
		return data;
	}

	public static <T> T loadBaseEntityById(SdsUserContext ctx, String type, String id) throws Exception {
		return loadBaseEntityById(ctx, type, id, null);
	}
	public static <T> T loadBaseEntityById(SdsUserContext ctx, String type, String id, Consumer<T> enhancer) throws Exception {
		String key = type+":"+id;
		BaseEntity cachedValue = (BaseEntity) ctx.getFromContextLocalStorage(key);
		if (cachedValue != null){
			if (cachedValue.getInternalType().equals(type) && cachedValue.getId().equals(id)) {
				return (T) cachedValue;
			}
		}
		T enObj = (T) ctx.getDAOGroup().loadBasicData(type, id);
		if (enhancer != null) {
			enhancer.accept(enObj);
		}
		ctx.putIntoContextLocalStorage(key, enObj);
		return enObj;
	}
	

	
	public static <T extends BaseEntity> void appendLinkToUrl(SdsUserContext ctx, List<T> list,
			Function<T, String> makeFunc) {
		if (list == null || list.isEmpty()) {
			return;
		}
		list.forEach(it -> {
			it.addItemToValueMap(SdsBaseConstants.X_LINK_TO_URL, makeFunc.apply(it));
		});
	}

	public static <T extends BaseEntity> void appendLinkToUrl(SdsUserContext ctx, T obj, String url) {
		if (obj == null) {
			return;
		}
		obj.addItemToValueMap(SdsBaseConstants.X_LINK_TO_URL, url);
	}

	public static ButtonViewComponent addAction(SdsUserContext ctx, BaseEntity obj, String title, String code,
			String linkToUrl) {
		ButtonViewComponent actionBtn = new ButtonViewComponent(title, null, code);
		actionBtn.setLinkToUrl(linkToUrl);
		List<ButtonViewComponent> actions = (List<ButtonViewComponent>) obj.valueByKey("actionList");
		if (actions == null) {
			actions = new ArrayList<>();
			obj.addItemToValueMap("actionList", actions);
		}
		actions.add(actionBtn);
		System.out.println("add action " + actionBtn.hashCode()+" to base-entity " + obj.hashCode());
		return actionBtn;
	}

	public static void setAction(SdsUserContext ctx, BaseEntity obj, String title, String code, String linkToUrl) {
		ButtonViewComponent actionBtn = new ButtonViewComponent(title, null, code);
		actionBtn.setLinkToUrl(linkToUrl);
		obj.addItemToValueMap("action", actionBtn);
	}
}
















