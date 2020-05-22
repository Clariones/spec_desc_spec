package com.cla.sds.uiaction;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class UiActionSerializer extends SdsObjectPlainCustomSerializer<UiAction>{

	@Override
	public void serialize(UiAction uiAction, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, uiAction, provider);
		
	}
}


