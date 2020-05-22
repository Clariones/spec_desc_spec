
package com.cla.sds.project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.company.Company;

public class ProjectMapper extends BaseRowMapper<Project>{
	
	protected Project internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Project project = getProject();		
		 		
 		setId(project, rs, rowNumber); 		
 		setName(project, rs, rowNumber); 		
 		setCompany(project, rs, rowNumber); 		
 		setVersion(project, rs, rowNumber);

		return project;
	}
	
	protected Project getProject(){
		return new Project();
	}		
		
	protected void setId(Project project, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(ProjectTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		project.setId(id);
	}
		
	protected void setName(Project project, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(ProjectTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		project.setName(name);
	}
		 		
 	protected void setCompany(Project project, ResultSet rs, int rowNumber) throws SQLException{
 		String companyId = rs.getString(ProjectTable.COLUMN_COMPANY);
 		if( companyId == null){
 			return;
 		}
 		if( companyId.isEmpty()){
 			return;
 		}
 		Company company = project.getCompany();
 		if( company != null ){
 			//if the root object 'project' already have the property, just set the id for it;
 			company.setId(companyId);
 			
 			return;
 		}
 		project.setCompany(createEmptyCompany(companyId));
 	}
 	
	protected void setVersion(Project project, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(ProjectTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		project.setVersion(version);
	}
		
		

 	protected Company  createEmptyCompany(String companyId){
 		Company company = new Company();
 		company.setId(companyId);
 		company.setVersion(Integer.MAX_VALUE);
 		return company;
 	}
 	
}


