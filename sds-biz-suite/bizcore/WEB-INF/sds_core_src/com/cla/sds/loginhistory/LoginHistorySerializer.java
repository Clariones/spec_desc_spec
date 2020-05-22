package com.cla.sds.loginhistory;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class LoginHistorySerializer extends SdsObjectPlainCustomSerializer<LoginHistory>{

	@Override
	public void serialize(LoginHistory loginHistory, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, loginHistory, provider);
		
	}
}


