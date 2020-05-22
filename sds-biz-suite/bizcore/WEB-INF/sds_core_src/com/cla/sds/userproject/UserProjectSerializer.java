package com.cla.sds.userproject;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class UserProjectSerializer extends SdsObjectPlainCustomSerializer<UserProject>{

	@Override
	public void serialize(UserProject userProject, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, userProject, provider);
		
	}
}


