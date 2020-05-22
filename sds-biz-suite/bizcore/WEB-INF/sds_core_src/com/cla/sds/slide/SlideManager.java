
package com.cla.sds.slide;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface SlideManager extends BaseManager{

		

	public Slide createSlide(SdsUserContext userContext, String name,int displayOrder,String imageUrl,String videoUrl,String linkToUrl,String pageId) throws Exception;
	public Slide updateSlide(SdsUserContext userContext,String slideId, int slideVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Slide loadSlide(SdsUserContext userContext, String slideId, String [] tokensExpr) throws Exception;
	public Slide internalSaveSlide(SdsUserContext userContext, Slide slide) throws Exception;
	public Slide internalSaveSlide(SdsUserContext userContext, Slide slide,Map<String,Object>option) throws Exception;

	public Slide transferToAnotherPage(SdsUserContext userContext, String slideId, String anotherPageId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String slideId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, Slide newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByPage(SdsUserContext userContext,String pageId) throws Exception;
	public Object listPageByPage(SdsUserContext userContext,String pageId, int start, int count) throws Exception;
  

}


