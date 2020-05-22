
package com.cla.sds.pagecontentspec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.user.User;
import com.cla.sds.project.Project;

public class PageContentSpecMapper extends BaseRowMapper<PageContentSpec>{
	
	protected PageContentSpec internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		PageContentSpec pageContentSpec = getPageContentSpec();		
		 		
 		setId(pageContentSpec, rs, rowNumber); 		
 		setScene(pageContentSpec, rs, rowNumber); 		
 		setBrief(pageContentSpec, rs, rowNumber); 		
 		setOwner(pageContentSpec, rs, rowNumber); 		
 		setProject(pageContentSpec, rs, rowNumber); 		
 		setVersion(pageContentSpec, rs, rowNumber);

		return pageContentSpec;
	}
	
	protected PageContentSpec getPageContentSpec(){
		return new PageContentSpec();
	}		
		
	protected void setId(PageContentSpec pageContentSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(PageContentSpecTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		pageContentSpec.setId(id);
	}
		
	protected void setScene(PageContentSpec pageContentSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String scene = rs.getString(PageContentSpecTable.COLUMN_SCENE);
		
		if(scene == null){
			//do nothing when nothing found in database
			return;
		}
		
		pageContentSpec.setScene(scene);
	}
		
	protected void setBrief(PageContentSpec pageContentSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String brief = rs.getString(PageContentSpecTable.COLUMN_BRIEF);
		
		if(brief == null){
			//do nothing when nothing found in database
			return;
		}
		
		pageContentSpec.setBrief(brief);
	}
		 		
 	protected void setOwner(PageContentSpec pageContentSpec, ResultSet rs, int rowNumber) throws SQLException{
 		String userId = rs.getString(PageContentSpecTable.COLUMN_OWNER);
 		if( userId == null){
 			return;
 		}
 		if( userId.isEmpty()){
 			return;
 		}
 		User user = pageContentSpec.getOwner();
 		if( user != null ){
 			//if the root object 'pageContentSpec' already have the property, just set the id for it;
 			user.setId(userId);
 			
 			return;
 		}
 		pageContentSpec.setOwner(createEmptyOwner(userId));
 	}
 	 		
 	protected void setProject(PageContentSpec pageContentSpec, ResultSet rs, int rowNumber) throws SQLException{
 		String projectId = rs.getString(PageContentSpecTable.COLUMN_PROJECT);
 		if( projectId == null){
 			return;
 		}
 		if( projectId.isEmpty()){
 			return;
 		}
 		Project project = pageContentSpec.getProject();
 		if( project != null ){
 			//if the root object 'pageContentSpec' already have the property, just set the id for it;
 			project.setId(projectId);
 			
 			return;
 		}
 		pageContentSpec.setProject(createEmptyProject(projectId));
 	}
 	
	protected void setVersion(PageContentSpec pageContentSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(PageContentSpecTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		pageContentSpec.setVersion(version);
	}
		
		

 	protected User  createEmptyOwner(String userId){
 		User user = new User();
 		user.setId(userId);
 		user.setVersion(Integer.MAX_VALUE);
 		return user;
 	}
 	
 	protected Project  createEmptyProject(String projectId){
 		Project project = new Project();
 		project.setId(projectId);
 		project.setVersion(Integer.MAX_VALUE);
 		return project;
 	}
 	
}


