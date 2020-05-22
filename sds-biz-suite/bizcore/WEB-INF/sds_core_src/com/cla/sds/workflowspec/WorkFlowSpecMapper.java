
package com.cla.sds.workflowspec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.user.User;
import com.cla.sds.project.Project;

public class WorkFlowSpecMapper extends BaseRowMapper<WorkFlowSpec>{
	
	protected WorkFlowSpec internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		WorkFlowSpec workFlowSpec = getWorkFlowSpec();		
		 		
 		setId(workFlowSpec, rs, rowNumber); 		
 		setScene(workFlowSpec, rs, rowNumber); 		
 		setBrief(workFlowSpec, rs, rowNumber); 		
 		setOwner(workFlowSpec, rs, rowNumber); 		
 		setProject(workFlowSpec, rs, rowNumber); 		
 		setVersion(workFlowSpec, rs, rowNumber);

		return workFlowSpec;
	}
	
	protected WorkFlowSpec getWorkFlowSpec(){
		return new WorkFlowSpec();
	}		
		
	protected void setId(WorkFlowSpec workFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(WorkFlowSpecTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		workFlowSpec.setId(id);
	}
		
	protected void setScene(WorkFlowSpec workFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String scene = rs.getString(WorkFlowSpecTable.COLUMN_SCENE);
		
		if(scene == null){
			//do nothing when nothing found in database
			return;
		}
		
		workFlowSpec.setScene(scene);
	}
		
	protected void setBrief(WorkFlowSpec workFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String brief = rs.getString(WorkFlowSpecTable.COLUMN_BRIEF);
		
		if(brief == null){
			//do nothing when nothing found in database
			return;
		}
		
		workFlowSpec.setBrief(brief);
	}
		 		
 	protected void setOwner(WorkFlowSpec workFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
 		String userId = rs.getString(WorkFlowSpecTable.COLUMN_OWNER);
 		if( userId == null){
 			return;
 		}
 		if( userId.isEmpty()){
 			return;
 		}
 		User user = workFlowSpec.getOwner();
 		if( user != null ){
 			//if the root object 'workFlowSpec' already have the property, just set the id for it;
 			user.setId(userId);
 			
 			return;
 		}
 		workFlowSpec.setOwner(createEmptyOwner(userId));
 	}
 	 		
 	protected void setProject(WorkFlowSpec workFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
 		String projectId = rs.getString(WorkFlowSpecTable.COLUMN_PROJECT);
 		if( projectId == null){
 			return;
 		}
 		if( projectId.isEmpty()){
 			return;
 		}
 		Project project = workFlowSpec.getProject();
 		if( project != null ){
 			//if the root object 'workFlowSpec' already have the property, just set the id for it;
 			project.setId(projectId);
 			
 			return;
 		}
 		workFlowSpec.setProject(createEmptyProject(projectId));
 	}
 	
	protected void setVersion(WorkFlowSpec workFlowSpec, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(WorkFlowSpecTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		workFlowSpec.setVersion(version);
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


