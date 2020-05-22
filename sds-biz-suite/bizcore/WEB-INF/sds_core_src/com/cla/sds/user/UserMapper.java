
package com.cla.sds.user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.company.Company;

public class UserMapper extends BaseRowMapper<User>{
	
	protected User internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		User user = getUser();		
		 		
 		setId(user, rs, rowNumber); 		
 		setName(user, rs, rowNumber); 		
 		setJoinTime(user, rs, rowNumber); 		
 		setCompany(user, rs, rowNumber); 		
 		setTitle(user, rs, rowNumber); 		
 		setVersion(user, rs, rowNumber);

		return user;
	}
	
	protected User getUser(){
		return new User();
	}		
		
	protected void setId(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(UserTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setId(id);
	}
		
	protected void setName(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(UserTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setName(name);
	}
		
	protected void setJoinTime(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date joinTime = rs.getDate(UserTable.COLUMN_JOIN_TIME);
		
		if(joinTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setJoinTime(joinTime);
	}
		 		
 	protected void setCompany(User user, ResultSet rs, int rowNumber) throws SQLException{
 		String companyId = rs.getString(UserTable.COLUMN_COMPANY);
 		if( companyId == null){
 			return;
 		}
 		if( companyId.isEmpty()){
 			return;
 		}
 		Company company = user.getCompany();
 		if( company != null ){
 			//if the root object 'user' already have the property, just set the id for it;
 			company.setId(companyId);
 			
 			return;
 		}
 		user.setCompany(createEmptyCompany(companyId));
 	}
 	
	protected void setTitle(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String title = rs.getString(UserTable.COLUMN_TITLE);
		
		if(title == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setTitle(title);
	}
		
	protected void setVersion(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(UserTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setVersion(version);
	}
		
		

 	protected Company  createEmptyCompany(String companyId){
 		Company company = new Company();
 		company.setId(companyId);
 		company.setVersion(Integer.MAX_VALUE);
 		return company;
 	}
 	
}


