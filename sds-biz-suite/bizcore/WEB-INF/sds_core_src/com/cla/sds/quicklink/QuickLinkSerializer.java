package com.cla.sds.quicklink;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class QuickLinkSerializer extends SdsObjectPlainCustomSerializer<QuickLink>{

	@Override
	public void serialize(QuickLink quickLink, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, quickLink, provider);
		
	}
}


