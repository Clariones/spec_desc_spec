
package com.cla.sds.changerequestspec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.user.User;
import com.cla.sds.project.Project;

public class ChangeRequestSpecMapper extends BaseRowMapper<ChangeRequestSpec>{
	
	protected ChangeRequestSpec internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		ChangeRequestSpec changeRequestSpec = getChangeRequestSpec();		
		 		
 		setId(changeRequestSpec, rs, rowNumber); 		
 		setScene(changeRequestSpec, rs, rowNumber); 		
 		setBrief(changeRequestSpec, rs, rowNumber); 		
 		setOwner(changeRequestSpec, rs, rowNumber); 		
 		setProject(changeRequestSpec, rs, rowNumber); 		
 		setVersion(changeRequestSpec, rs, rowNumber);

		return changeRequestSpec;
	}
	
	protected ChangeRequestSpec getChangeRequestSpec(){
		return new ChangeRequestSpec();
	}		
		
	protected void setId(ChangeRequestSpec changeRequestSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(ChangeRequestSpecTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		changeRequestSpec.setId(id);
	}
		
	protected void setScene(ChangeRequestSpec changeRequestSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String scene = rs.getString(ChangeRequestSpecTable.COLUMN_SCENE);
		
		if(scene == null){
			//do nothing when nothing found in database
			return;
		}
		
		changeRequestSpec.setScene(scene);
	}
		
	protected void setBrief(ChangeRequestSpec changeRequestSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String brief = rs.getString(ChangeRequestSpecTable.COLUMN_BRIEF);
		
		if(brief == null){
			//do nothing when nothing found in database
			return;
		}
		
		changeRequestSpec.setBrief(brief);
	}
		 		
 	protected void setOwner(ChangeRequestSpec changeRequestSpec, ResultSet rs, int rowNumber) throws SQLException{
 		String userId = rs.getString(ChangeRequestSpecTable.COLUMN_OWNER);
 		if( userId == null){
 			return;
 		}
 		if( userId.isEmpty()){
 			return;
 		}
 		User user = changeRequestSpec.getOwner();
 		if( user != null ){
 			//if the root object 'changeRequestSpec' already have the property, just set the id for it;
 			user.setId(userId);
 			
 			return;
 		}
 		changeRequestSpec.setOwner(createEmptyOwner(userId));
 	}
 	 		
 	protected void setProject(ChangeRequestSpec changeRequestSpec, ResultSet rs, int rowNumber) throws SQLException{
 		String projectId = rs.getString(ChangeRequestSpecTable.COLUMN_PROJECT);
 		if( projectId == null){
 			return;
 		}
 		if( projectId.isEmpty()){
 			return;
 		}
 		Project project = changeRequestSpec.getProject();
 		if( project != null ){
 			//if the root object 'changeRequestSpec' already have the property, just set the id for it;
 			project.setId(projectId);
 			
 			return;
 		}
 		changeRequestSpec.setProject(createEmptyProject(projectId));
 	}
 	
	protected void setVersion(ChangeRequestSpec changeRequestSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(ChangeRequestSpecTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		changeRequestSpec.setVersion(version);
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


