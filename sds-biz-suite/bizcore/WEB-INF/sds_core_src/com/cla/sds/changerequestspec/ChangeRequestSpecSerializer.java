package com.cla.sds.changerequestspec;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class ChangeRequestSpecSerializer extends SdsObjectPlainCustomSerializer<ChangeRequestSpec>{

	@Override
	public void serialize(ChangeRequestSpec changeRequestSpec, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, changeRequestSpec, provider);
		
	}
}


