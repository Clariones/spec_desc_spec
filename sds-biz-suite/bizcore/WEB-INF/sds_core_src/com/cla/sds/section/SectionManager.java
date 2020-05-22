
package com.cla.sds.section;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface SectionManager extends BaseManager{

		

	public Section createSection(SdsUserContext userContext, String title,String brief,String icon,int displayOrder,String viewGroup,String linkToUrl,String pageId) throws Exception;
	public Section updateSection(SdsUserContext userContext,String sectionId, int sectionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Section loadSection(SdsUserContext userContext, String sectionId, String [] tokensExpr) throws Exception;
	public Section internalSaveSection(SdsUserContext userContext, Section section) throws Exception;
	public Section internalSaveSection(SdsUserContext userContext, Section section,Map<String,Object>option) throws Exception;

	public Section transferToAnotherPage(SdsUserContext userContext, String sectionId, String anotherPageId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String sectionId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, Section newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByPage(SdsUserContext userContext,String pageId) throws Exception;
	public Object listPageByPage(SdsUserContext userContext,String pageId, int start, int count) throws Exception;
  

}


