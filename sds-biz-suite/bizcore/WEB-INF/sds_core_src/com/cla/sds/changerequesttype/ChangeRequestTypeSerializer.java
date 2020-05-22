package com.cla.sds.changerequesttype;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class ChangeRequestTypeSerializer extends SdsObjectPlainCustomSerializer<ChangeRequestType>{

	@Override
	public void serialize(ChangeRequestType changeRequestType, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, changeRequestType, provider);
		
	}
}


