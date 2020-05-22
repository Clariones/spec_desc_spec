
package com.cla.sds.pageflowspec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.user.User;
import com.cla.sds.project.Project;

public class PageFlowSpecMapper extends BaseRowMapper<PageFlowSpec>{
	
	protected PageFlowSpec internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		PageFlowSpec pageFlowSpec = getPageFlowSpec();		
		 		
 		setId(pageFlowSpec, rs, rowNumber); 		
 		setScene(pageFlowSpec, rs, rowNumber); 		
 		setBrief(pageFlowSpec, rs, rowNumber); 		
 		setOwner(pageFlowSpec, rs, rowNumber); 		
 		setProject(pageFlowSpec, rs, rowNumber); 		
 		setVersion(pageFlowSpec, rs, rowNumber);

		return pageFlowSpec;
	}
	
	protected PageFlowSpec getPageFlowSpec(){
		return new PageFlowSpec();
	}		
		
	protected void setId(PageFlowSpec pageFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(PageFlowSpecTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		pageFlowSpec.setId(id);
	}
		
	protected void setScene(PageFlowSpec pageFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String scene = rs.getString(PageFlowSpecTable.COLUMN_SCENE);
		
		if(scene == null){
			//do nothing when nothing found in database
			return;
		}
		
		pageFlowSpec.setScene(scene);
	}
		
	protected void setBrief(PageFlowSpec pageFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String brief = rs.getString(PageFlowSpecTable.COLUMN_BRIEF);
		
		if(brief == null){
			//do nothing when nothing found in database
			return;
		}
		
		pageFlowSpec.setBrief(brief);
	}
		 		
 	protected void setOwner(PageFlowSpec pageFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
 		String userId = rs.getString(PageFlowSpecTable.COLUMN_OWNER);
 		if( userId == null){
 			return;
 		}
 		if( userId.isEmpty()){
 			return;
 		}
 		User user = pageFlowSpec.getOwner();
 		if( user != null ){
 			//if the root object 'pageFlowSpec' already have the property, just set the id for it;
 			user.setId(userId);
 			
 			return;
 		}
 		pageFlowSpec.setOwner(createEmptyOwner(userId));
 	}
 	 		
 	protected void setProject(PageFlowSpec pageFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
 		String projectId = rs.getString(PageFlowSpecTable.COLUMN_PROJECT);
 		if( projectId == null){
 			return;
 		}
 		if( projectId.isEmpty()){
 			return;
 		}
 		Project project = pageFlowSpec.getProject();
 		if( project != null ){
 			//if the root object 'pageFlowSpec' already have the property, just set the id for it;
 			project.setId(projectId);
 			
 			return;
 		}
 		pageFlowSpec.setProject(createEmptyProject(projectId));
 	}
 	
	protected void setVersion(PageFlowSpec pageFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(PageFlowSpecTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		pageFlowSpec.setVersion(version);
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


