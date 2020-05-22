
package com.cla.sds.userproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.user.User;
import com.cla.sds.project.Project;

public class UserProjectMapper extends BaseRowMapper<UserProject>{
	
	protected UserProject internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		UserProject userProject = getUserProject();		
		 		
 		setId(userProject, rs, rowNumber); 		
 		setUser(userProject, rs, rowNumber); 		
 		setProject(userProject, rs, rowNumber); 		
 		setCreateTime(userProject, rs, rowNumber); 		
 		setLastUpdateTime(userProject, rs, rowNumber); 		
 		setVersion(userProject, rs, rowNumber);

		return userProject;
	}
	
	protected UserProject getUserProject(){
		return new UserProject();
	}		
		
	protected void setId(UserProject userProject, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(UserProjectTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		userProject.setId(id);
	}
		 		
 	protected void setUser(UserProject userProject, ResultSet rs, int rowNumber) throws SQLException{
 		String userId = rs.getString(UserProjectTable.COLUMN_USER);
 		if( userId == null){
 			return;
 		}
 		if( userId.isEmpty()){
 			return;
 		}
 		User user = userProject.getUser();
 		if( user != null ){
 			//if the root object 'userProject' already have the property, just set the id for it;
 			user.setId(userId);
 			
 			return;
 		}
 		userProject.setUser(createEmptyUser(userId));
 	}
 	 		
 	protected void setProject(UserProject userProject, ResultSet rs, int rowNumber) throws SQLException{
 		String projectId = rs.getString(UserProjectTable.COLUMN_PROJECT);
 		if( projectId == null){
 			return;
 		}
 		if( projectId.isEmpty()){
 			return;
 		}
 		Project project = userProject.getProject();
 		if( project != null ){
 			//if the root object 'userProject' already have the property, just set the id for it;
 			project.setId(projectId);
 			
 			return;
 		}
 		userProject.setProject(createEmptyProject(projectId));
 	}
 	
	protected void setCreateTime(UserProject userProject, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date createTime = rs.getTimestamp(UserProjectTable.COLUMN_CREATE_TIME);
		
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		userProject.setCreateTime(convertToDateTime(createTime));
	}
		
	protected void setLastUpdateTime(UserProject userProject, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date lastUpdateTime = rs.getTimestamp(UserProjectTable.COLUMN_LAST_UPDATE_TIME);
		
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		userProject.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		
	protected void setVersion(UserProject userProject, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(UserProjectTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		userProject.setVersion(version);
	}
		
		

 	protected User  createEmptyUser(String userId){
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


