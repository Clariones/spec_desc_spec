
package com.cla.sds.company;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.platform.Platform;

public class CompanyMapper extends BaseRowMapper<Company>{
	
	protected Company internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Company company = getCompany();		
		 		
 		setId(company, rs, rowNumber); 		
 		setName(company, rs, rowNumber); 		
 		setCreateTime(company, rs, rowNumber); 		
 		setPlatform(company, rs, rowNumber); 		
 		setVersion(company, rs, rowNumber);

		return company;
	}
	
	protected Company getCompany(){
		return new Company();
	}		
		
	protected void setId(Company company, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(CompanyTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		company.setId(id);
	}
		
	protected void setName(Company company, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(CompanyTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		company.setName(name);
	}
		
	protected void setCreateTime(Company company, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date createTime = rs.getTimestamp(CompanyTable.COLUMN_CREATE_TIME);
		
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		company.setCreateTime(convertToDateTime(createTime));
	}
		 		
 	protected void setPlatform(Company company, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(CompanyTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = company.getPlatform();
 		if( platform != null ){
 			//if the root object 'company' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		company.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(Company company, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(CompanyTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		company.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


